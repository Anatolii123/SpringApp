package application.dao;

import application.entity.People;

import javax.servlet.http.HttpSession;

public interface UserDao {

    void save(People user, HttpSession httpSession);

    People logIn(String email, String password, HttpSession httpSession);

    int createId();

    boolean checkEntityInDatabase(People user);

    boolean checkEmailInDatabase(People user);
}
