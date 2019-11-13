package application.controller;

import application.dao.SaltResponse;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class UserRestController {

    @CrossOrigin(value = "*")
    @GetMapping("/Salt")
    public SaltResponse getSalt(HttpSession session) {
        SaltResponse response = new SaltResponse();
        response.setSalt(Long.toHexString((long) ((Math.random() * 900000000000000000L) + 100000000000000000L)));
        session.setAttribute("salt", response.getSalt());
        return response;
    }
}
