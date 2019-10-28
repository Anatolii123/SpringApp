package application.service;

import application.EmptyPasswordException;
import application.WrongPasswordException;
import application.entity.People;

public interface UserService {
    People logIn(String email, String password) throws EmptyPasswordException, WrongPasswordException;
    void save(People user);
}
