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

import java.math.BigInteger;

import static application.dao.UserDaoImpl.hashPassword;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    public UserService userService;

    public void setEmailPassword(HttpSession session, String email, String password) {
        session.setAttribute("email", email);
        session.setAttribute("password", password);
    }

    public BigInteger diffieHellman(BigInteger num, BigInteger exp) {
        BigInteger key = num.pow(exp.intValue()).mod(BigInteger.valueOf(983));
        return key;
    }

    public static String decodePassword(String password, BigInteger key) {
        String decodedPassword = new BigInteger(password, 16).xor(key).toString(16);
        return decodedPassword;
    }

    @GetMapping("/index")
    public ModelAndView defaultPage(HttpSession session) {
        session.setAttribute("password", "");

        return new ModelAndView("/index");
    }

//    @GetMapping("/SignUp")
//    public ModelAndView createUserPage(Model model) {
//        model.addAttribute("user", new People());
//
//        return new ModelAndView("SignUp");
//    }

    @PostMapping("/SignUp")
    public ModelAndView createUserPage(Model model) {
        model.addAttribute("user", new People());

        return new ModelAndView("SignUp");
    }

    @GetMapping("/")
    public ModelAndView loginPage(HttpSession session) {
        session.setAttribute("loginError", "");
        session.setAttribute("password", "");
        return new ModelAndView("redirect:/");
    }

    @PostMapping(value = "/addUser")
    public ModelAndView addUser(Model model, @ModelAttribute("user") People user, BindingResult bindingResult,
                                HttpServletRequest request, HttpSession session) throws EmptyPasswordException, WrongPasswordException {
        try {
            BigInteger resultKey = diffieHellman(BigInteger.valueOf(Integer.parseInt(request.getParameter("publicValue"))),
                    (BigInteger) session.getAttribute("privateKey"));
            session.setAttribute("resultKey", resultKey);
            user.setPassword(decodePassword(user.getPassword(), resultKey));
            userService.save(user, request, session);
        } catch (EntityExistsException e) {
            session.setAttribute("registration", "");
            String newPassword = hashPassword(user.getEmail() +
                    user.getPassword() + session.getAttribute("salt"));
            setEmailPassword(session, user.getEmail(), newPassword);
            user = userService.logIn(user.getEmail(), newPassword, session);
            model.addAttribute("user", user);
            return new ModelAndView("/View");
        } catch (WrongPasswordException e) {
            session.setAttribute("Error", "Пользователь с таким аккаунтом уже существует! Попробуйте ещё раз.");
            request.setAttribute("user", user);
            model.addAttribute("user", user);
            return new ModelAndView("redirect:/SignUp");
        } catch (WrongPasswordCopyException e) {
            session.setAttribute("Error", "Копия пароля введена неверно! Попробуйте ещё раз.");
            return new ModelAndView("redirect:/SignUp");
        }
        session.setAttribute("Error", "");
        session.setAttribute("registration", "Вы успешно зарегистрированы!");
        setEmailPassword(session, user.getEmail(), user.getPassword());
        model.addAttribute("user", user);
        return new ModelAndView("/View");
    }

    @RequestMapping(value = "/View", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView logIn(Model model, HttpServletRequest request, HttpSession session) {
        if (request.getMethod().equals("POST")) {
            BigInteger privateKey = BigInteger.valueOf((long) (Math.random() * 1000));
            session.setAttribute("privateKey", privateKey);
            BigInteger publicKey = diffieHellman(BigInteger.valueOf(1000), privateKey);
            session.setAttribute("publicValue", publicKey);
            session.setAttribute("resultKey", 0);
            String password = request.getParameter("PASSWORD");
            setEmailPassword(session, request.getParameter("EMAIL"), password);
        }
        String email = session.getAttribute("email").toString();
        String password = session.getAttribute("password").toString();
        People user;
        try {
            user = userService.logIn(email, password, session);
        } catch (EmptyPasswordException e) {
            session.setAttribute("email", request.getParameter("EMAIL"));
            session.setAttribute("loginError", "Введите, пожалуйста, пароль.");
            model.addAttribute("user", null);
            return new ModelAndView("redirect:/");
        } catch (WrongPasswordException e) {
            setEmailPassword(session, request.getParameter("EMAIL"), "");
            session.setAttribute("loginError", "Пароль введён неверно! Попробуйте ещё раз.");
            model.addAttribute("user", null);
            return new ModelAndView("redirect:/");
        }
        if (user == null) {
            session.setAttribute("loginError", "Введите email и пароль!");
            model.addAttribute("user", null);
            return new ModelAndView("redirect:/");
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
        matrixCalc.doPost(request, session);
        model.addAttribute("user", session.getAttribute("user"));
        return new ModelAndView("View");
    }

    @GetMapping("/LogOut")
    public ModelAndView logOut(HttpSession session, Model model) {
        setEmailPassword(session, "", "");
        model.addAttribute("user", null);

        return new ModelAndView("redirect:/");
    }

}
