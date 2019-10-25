package application.service;

import application.dao.UserDao;
import application.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    public static final String EMPTY_STRING = "";

    @Autowired
    public UserDao userDao;

    @Override
    public People logIn(String email, String password) throws Exception {

        if (email == null || EMPTY_STRING.equals(email)) {
            throw new Exception();
        }

        return userDao.logIn(email,password);
    }

    @Override
    public void save(People user) {
        userDao.save(user);
    }
}
