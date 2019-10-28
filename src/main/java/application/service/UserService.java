package application.service;

import application.exceptions.EmptyPasswordException;
import application.exceptions.EntityExistsException;
import application.exceptions.WrongPasswordCopyException;
import application.exceptions.WrongPasswordException;
import application.entity.People;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    People logIn(String email, String password) throws EmptyPasswordException, WrongPasswordException;
    void save(People user, HttpServletRequest request) throws EntityExistsException, WrongPasswordException, WrongPasswordCopyException;
}
