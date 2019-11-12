package application.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class UserRestController {

    @CrossOrigin(value = "*")
    @GetMapping("/Salt")
    public String getSalt(HttpSession session) {
        String salt = Long.toHexString((long) ((Math.random() * 900000000000000000L) + 100000000000000000L));
        session.setAttribute("salt", salt);
        return salt;
    }

}
