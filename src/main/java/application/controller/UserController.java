package application.controller;

import application.entity.User;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/SignUp")
    public String createUserPage(Model model) {
        model.addAttribute("user", new User());
      return "SignUp";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userService.save(user);
        return "View";
    }

//    @PostMapping("/addUser")
//    public String addUser(@ModelAttribute("user") User user) {
//        userService.save(user);
//        return "redirect:/View";
//    }

    @PostMapping("/View")
    public String logIn(Model model, @RequestParam(name = "EMAIL") String email,
                                  @RequestParam(name = "PASSWORD") String password) {
        User user = userService.logIn(email, password);
        if (user == null) {
            return "redirect:/SignUp";
        }
        model.addAttribute("user", user);

        return "View";
    }
}
