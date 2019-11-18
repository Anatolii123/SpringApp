package application.service;

import application.exceptions.EmptyPasswordException;
import application.exceptions.EntityExistsException;
import application.exceptions.WrongPasswordCopyException;
import application.exceptions.WrongPasswordException;
import application.dao.UserDao;
import application.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.math.BigInteger;

import static application.controller.UserController.decodePassword;
import static application.dao.UserDaoImpl.hashPassword;

@Service
public class UserServiceImpl implements UserService {

    public static final String EMPTY_STRING = "";

    @Autowired
    public UserDao userDao;

    @Override
    public People logIn(String email, String password, HttpSession session) throws EmptyPasswordException, WrongPasswordException {

        if (password == null || EMPTY_STRING.equals(password) && !EMPTY_STRING.equals(email)) {
            throw new EmptyPasswordException();
        }

        People user = userDao.logIn(email, password, session);
        if (user != null) {
            String newPassword = hashPassword(email + user.getPassword() + session.getAttribute("salt"));
            if (!user.getPassword().equals(password) && !newPassword.equals(password)) {
                throw new WrongPasswordException();
            }
        }

        return user;
    }

    @Override
    public void save(People user, HttpServletRequest request, HttpSession httpSession)
            throws EntityExistsException, WrongPasswordException, WrongPasswordCopyException {
        if (userDao.checkEntityInDatabase(user)) {
            throw new EntityExistsException();
        }
        if (userDao.checkEmailInDatabase(user)) {
            throw new WrongPasswordException();
        }
//        if (!user.getPassword().equals(decodePassword(request.getParameter("COPY_PASSWORD"),
//                (BigInteger) httpSession.getAttribute("resultKey")) )) {
//            throw new WrongPasswordCopyException();
//        }
        userDao.save(user, httpSession);
    }
}
