package application.service;

import application.entity.People;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    People logIn(String email, String password) throws Exception;
    void save(People user);
}
