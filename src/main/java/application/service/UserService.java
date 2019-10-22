package application.service;

import application.entity.User;
import java.util.List;

public interface UserService {
    User logIn(String email, String password);
}
