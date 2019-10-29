package application.controller;

import application.MatrixCalc;
import application.exceptions.EmptyPasswordException;
import application.exceptions.EntityExistsException;
import application.exceptions.WrongPasswordCopyException;
import application.exceptions.WrongPasswordException;
import application.entity.People;
import application.factory.*;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    public String createUserPage(Model model) {
        model.addAttribute("user", new People());

      return "SignUp";
    }

    @GetMapping("/")
    public String loginPage(HttpServletRequest request) {
        request.getSession().setAttribute("loginError","");

        return "redirect:/";
    }

    @PostMapping(value = "/addUser")
    public String addUser(Model model, @ModelAttribute("user") People user, BindingResult bindingResult,
                          HttpServletRequest request, HttpSession session) throws EmptyPasswordException, WrongPasswordException {
        try {
            userService.save(user, request);
        } catch (EntityExistsException e) {
            session.setAttribute("registration", "");
            setEmailPassword(session,user.getEmail(),user.getPassword());
            user = userService.logIn(user.getEmail(), user.getPassword());
            model.addAttribute("user", user);
            return "/View";
        } catch (WrongPasswordException e) {
            session.setAttribute("Error","Пользователь с таким аккаунтом уже существует! Попробуйте ещё раз.");
            request.setAttribute("user", user);
            model.addAttribute("user", user);
            return "redirect:/SignUp";
        } catch (WrongPasswordCopyException e) {
            session.setAttribute("Error","Копия пароля введена неверно! Попробуйте ещё раз.");
            request.setAttribute("user", user);
            model.addAttribute("user", user);
            return "redirect:/SignUp";
        }
        session.setAttribute("Error","");
        session.setAttribute("registration", "Вы успешно зарегистрированы!");
        setEmailPassword(session,user.getEmail(),user.getPassword());
        model.addAttribute("user", user);
        return "/View";
    }

    @RequestMapping(value="/View", method = { RequestMethod.POST, RequestMethod.GET })
    public String logIn(Model model, HttpServletRequest request, HttpSession session) {
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
            return "redirect:/";
        } catch (WrongPasswordException e) {
            session.setAttribute("email",request.getParameter("EMAIL"));
            session.setAttribute("loginError","Пароль введён неверно! Попробуйте ещё раз.");
            return "redirect:/";
        }
        if (user == null) {
            return "redirect:/SignUp";
        }
        model.addAttribute("user", user);
        session.setAttribute("user", user);
        session.setAttribute("registration", "");
        session.setAttribute("loginError", "");
        return "View";
    }

    @PostMapping("/MatrixCalc")
    public String calculate(HttpServletRequest request, HttpSession session, Model model) {
        MatrixCalc matrixCalc = new MatrixCalc();
        matrixCalc.doPost(request,session);
        model.addAttribute("user",session.getAttribute("user"));
        return "View";
    }

    @GetMapping("/LogOut")
    public String logOut(HttpSession session) {
        setEmailPassword(session,"","");
        return "redirect:/";
    }



}
