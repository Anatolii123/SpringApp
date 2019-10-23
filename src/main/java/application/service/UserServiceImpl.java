package application.service;

import application.dao.UserDao;
import application.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;

    @Override
    public User logIn(String email, String password) {
        return userDao.logIn(email,password);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }
}
