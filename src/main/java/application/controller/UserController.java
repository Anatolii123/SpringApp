package application.controller;

import application.MatrixCalc;
import application.exceptions.EmptyPasswordException;
import application.exceptions.EntityExistsException;
import application.exceptions.WrongPasswordCopyException;
import application.exceptions.WrongPasswordException;
import application.entity.People;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    public UserService userService;

    public void setEmailPassword(HttpSession session, String email, String password) {
        session.setAttribute("email", email);
        session.setAttribute("password", password);
    }

    @GetMapping("/SignUp")
    public ModelAndView createUserPage(Model model) {
        model.addAttribute("user", new People());

      return new ModelAndView("SignUp");
    }

    @GetMapping("/")
    public ModelAndView loginPage(HttpServletRequest request) {
        request.getSession().setAttribute("loginError","");

        return new ModelAndView("redirect:/");
    }

    @PostMapping(value = "/addUser")
    public ModelAndView addUser(Model model, @ModelAttribute("user") People user, BindingResult bindingResult,
                          HttpServletRequest request, HttpSession session) throws EmptyPasswordException, WrongPasswordException {
        try {
            userService.save(user, request);
        } catch (EntityExistsException e) {
            session.setAttribute("registration", "");
            setEmailPassword(session,user.getEmail(),user.getPassword());
            user = userService.logIn(user.getEmail(), user.getPassword());
            model.addAttribute("user", user);
            return new ModelAndView("/View");
        } catch (WrongPasswordException e) {
            session.setAttribute("Error","Пользователь с таким аккаунтом уже существует! Попробуйте ещё раз.");
            request.setAttribute("user", user);
            model.addAttribute("user", user);
            return new ModelAndView("redirect:/SignUp");
        } catch (WrongPasswordCopyException e) {
            session.setAttribute("Error","Копия пароля введена неверно! Попробуйте ещё раз.");
            return new ModelAndView("redirect:/SignUp");
        }
        session.setAttribute("Error","");
        session.setAttribute("registration", "Вы успешно зарегистрированы!");
        setEmailPassword(session,user.getEmail(),user.getPassword());
        model.addAttribute("user", user);
        return new ModelAndView("/View");
    }

    @RequestMapping(value="/View", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView logIn(Model model, HttpServletRequest request, HttpSession session) {
        if (request.getMethod().equals("POST")) {
            setEmailPassword(session,request.getParameter("EMAIL"),request.getParameter("PASSWORD"));
        }
        String email = request.getSession().getAttribute("email").toString();
        String password = request.getSession().getAttribute("password").toString();
        People user;
        try {
            user = userService.logIn(email, password);
        } catch (EmptyPasswordException e) {
            session.setAttribute("email",request.getParameter("EMAIL"));
            session.setAttribute("loginError","Введите, пожалуйста, пароль.");
            model.addAttribute("user", null);
            return new ModelAndView("redirect:/");
        } catch (WrongPasswordException e) {
            session.setAttribute("email",request.getParameter("EMAIL"));
            session.setAttribute("loginError","Пароль введён неверно! Попробуйте ещё раз.");
            model.addAttribute("user", null);
            return new ModelAndView("redirect:/");
        }
        if (user == null) {
            return new ModelAndView("redirect:/SignUp");
        }
        model.addAttribute("user", user);
        session.setAttribute("user", user);
        session.setAttribute("registration", "");
        session.setAttribute("loginError", "");
        return new ModelAndView("View");
    }

    @PostMapping("/MatrixCalc")
    public ModelAndView calculate(HttpServletRequest request, HttpSession session, Model model) {
        MatrixCalc matrixCalc = new MatrixCalc();
        matrixCalc.doPost(request,session);
        model.addAttribute("user",session.getAttribute("user"));
        return new ModelAndView("View");
    }

    @GetMapping("/LogOut")
    public ModelAndView logOut(HttpSession session) {
        setEmailPassword(session,"","");
        return new ModelAndView("redirect:/");
    }
}
