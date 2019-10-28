package application.service;

import application.EmptyPasswordException;
import application.WrongPasswordCopyException;
import application.WrongPasswordException;
import application.dao.UserDao;
import application.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if (user!= null && user.getPassword() != password) {
            throw new WrongPasswordException();
        }

        return user;
    }

    @Override
    public boolean save(People user) throws WrongPasswordCopyException {
        if (!user.getPassword().equals(user.getCopyPassword())) {
            throw new WrongPasswordCopyException();
        }
        if (!userDao.checkEntityInDatabase(user)) {
            userDao.save(user);
            return true;
        }
        return false;
    }
}
