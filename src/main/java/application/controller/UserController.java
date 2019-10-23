package application.controller;

import application.entity.User;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/SignUp")
    public String signUp(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("SignUp");

        return "SignUp";
    }

    @PostMapping("/View")
    public String logIn(HttpServletRequest request) {
        User user = userService.logIn(request.getParameter("EMAIL"), request.getParameter("PASSWORD"));
        if (user == null) {
                return "redirect:/SignUp";
        }
        ModelAndView modelAndView = new ModelAndView("View");
        modelAndView.addObject("user", user);

         return "View";
    }
}
