package application.dao;

import application.entity.People;
import application.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserDao {

    void save(People user);

    People logIn(String email, String password);

    int createId();
}
