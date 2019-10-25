package application.service;

import application.entity.People;

public interface UserService {
    People logIn(String email, String password);
    void save(People user);
}
