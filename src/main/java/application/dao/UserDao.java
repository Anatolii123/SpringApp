package application.dao;

import application.entity.People;

public interface UserDao {

    void save(People user);

    People logIn(String email, String password);

    int createId();

    boolean checkEntityInDatabase(People user);

    boolean checkEmailInDatabase(People user);
}
