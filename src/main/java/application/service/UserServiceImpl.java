package application.service;

import application.exceptions.EmptyPasswordException;
import application.exceptions.EntityExistsException;
import application.exceptions.WrongPasswordCopyException;
import application.exceptions.WrongPasswordException;
import application.dao.UserDao;
import application.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    public static final String EMPTY_STRING = "";

    @Autowired
    public UserDao userDao;

    @Override
    public People logIn(String email, String password) throws EmptyPasswordException, WrongPasswordException {

        if (password == null || EMPTY_STRING.equals(password) && !EMPTY_STRING.equals(email)) {
            throw new EmptyPasswordException();
        }

        People user = userDao.logIn(email,password);

        if (user!= null && !user.getPassword().equals(password)) {
            throw new WrongPasswordException();
        }

        return user;
    }

    @Override
    public void save(People user, HttpServletRequest request) throws EntityExistsException, WrongPasswordCopyException {
        if (userDao.checkEntityInDatabase(user) == true) {
            throw new EntityExistsException();
        }
        if (!user.getPassword().equals(request.getParameter("COPY_PASSWORD"))) {
            throw new WrongPasswordCopyException();
        }
        userDao.save(user);
    }
}
