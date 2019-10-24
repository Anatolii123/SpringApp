package application.controller;

import application.entity.User;
import application.service.UserService;
import com.sun.org.apache.xpath.internal.operations.String;
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
        model.addAttribute("user", new User());
      return "SignUp";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userService.save(user);
        return "redirect:/View";
    }

//    @PostMapping("/addUser")
//    public String addUser(@ModelAttribute("user") User user) {
//        userService.save(user);
//        return "redirect:/View";
//    }

    @PostMapping("/View")
    public java.lang.String logIn(HttpServletRequest request, Model model, @RequestParam(name = "EMAIL") java.lang.String email,
                                  @RequestParam(name = "PASSWORD") java.lang.String password) {
        User user = userService.logIn(email, password);
        if (user == null) {
            return "redirect:/SignUp";
        }
        model.addAttribute("user", user);

        return "View";
    }
}
