package application.controller;

import application.exceptions.EmptyPasswordException;
import application.exceptions.EntityExistsException;
import application.exceptions.WrongPasswordCopyException;
import application.exceptions.WrongPasswordException;
import application.entity.People;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import static javax.servlet.DispatcherType.REQUEST;
import static org.springframework.http.HttpMethod.GET;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/SignUp")
    public String createUserPage(Model model) {
        model.addAttribute("user", new People());

      return "SignUp";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute("user") People user, BindingResult bindingResult, HttpServletRequest request) {
        String name = user.getName();
        try {
            userService.save(user, request);
        } catch (EntityExistsException e) {
            return "/View";
        } catch (WrongPasswordCopyException e) {
            request.getSession().setAttribute("passwordCopyError","Копия пароля введена неверно.");
            user.setName(name);
            return "redirect:/SignUp";
        }
        request.getSession().setAttribute("registration", "Вы успешно зарегистрированы!");

        return "/View";
    }

    @RequestMapping(value="/View", method = { RequestMethod.POST, RequestMethod.GET })
    public String logIn(Model model,HttpServletRequest request) {
//        if (RequestMethod.POST..matches(GET.toString())) {
//            System.out.println("asd");
//        }
        request.getSession().setAttribute("email", request.getParameter("EMAIL"));
        request.getSession().setAttribute("password", request.getParameter("PASSWORD"));
        String email = request.getSession().getAttribute("email").toString();
        String password = request.getSession().getAttribute("password").toString();
        People user;
        try {
            user = userService.logIn(email, password);
        } catch (EmptyPasswordException e) {
            request.getSession().setAttribute("email",request.getParameter("EMAIL"));
            request.getSession().setAttribute("loginError","Введите, пожалуйста, пароль.");
            return "redirect:/";
        } catch (WrongPasswordException e) {
            request.getSession().setAttribute("email",request.getParameter("EMAIL"));
            request.getSession().setAttribute("loginError","Пароль введён неверно! Попробуйте ещё раз.");
            return "redirect:/";
        }
        if (user == null) {
            return "redirect:/SignUp";
        }
        model.addAttribute("user", user);
        request.getSession().setAttribute("registration", "");
        return "View";
    }

}
