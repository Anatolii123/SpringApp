package application.service;

import application.exceptions.EmptyPasswordException;
import application.exceptions.EntityExistsException;
import application.exceptions.WrongPasswordCopyException;
import application.exceptions.WrongPasswordException;
import application.entity.People;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserService {
    People logIn(String email, String password, HttpSession session) throws EmptyPasswordException, WrongPasswordException;
    void save(People user, HttpServletRequest request, HttpSession httpSession) throws EntityExistsException, WrongPasswordException, WrongPasswordCopyException;
}
