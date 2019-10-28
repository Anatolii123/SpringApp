package application.controller;

import application.EmptyPasswordException;
import application.WrongPasswordException;
import application.entity.People;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

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
        if (userService.save(user) == true) {
            request.getSession().setAttribute("registration", "Вы успешно зарегистрированы!");
        }

        return "View";
    }

    @PostMapping("/View")
    public String logIn(Model model, @RequestParam(name = "EMAIL") String email,
                                  @RequestParam(name = "PASSWORD") String password, HttpServletRequest request) {
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
