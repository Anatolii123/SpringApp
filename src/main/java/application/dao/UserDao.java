package application.dao;

import application.entity.People;
import application.entity.User;
import java.util.List;

public interface UserDao {

    void save(People user);

    People logIn(String email, String password);

    void update(People user);

    void logOut();

}
