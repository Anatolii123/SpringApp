package application.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface HasLogout {
    @PostMapping(value = "/logout", params = {"login"})
    Boolean logout(@RequestParam("login") String login);
}
