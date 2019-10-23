package application.controller;

import application.entity.User;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/SignUp")
    public String createUserPage() {
        return "SignUp";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/View";
    }

    @PostMapping("/View")
    public String logIn(HttpServletRequest request, Model model) {
        User user = userService.logIn(request.getParameter("EMAIL"), request.getParameter("PASSWORD"));
        if (user == null) {
            return "redirect:/SignUp";
        }
        model.addAttribute("user", user);
        return "View";
    }
}
