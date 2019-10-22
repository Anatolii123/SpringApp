package application.dao;

import application.entity.User;
import java.util.List;

public interface UserDao {

    void save(User user);

    User logIn(String email, String password);

    void update(User user);

    void logOut();

}
