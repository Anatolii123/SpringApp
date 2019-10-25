package application.service;

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
    public People logIn(String email, String password) throws Exception {

        if (password == null || EMPTY_STRING.equals(password)) {
            throw new Exception();
        }

        return userDao.logIn(email,password);
    }

    @Override
    public void save(People user) {
        userDao.save(user);
    }
}
