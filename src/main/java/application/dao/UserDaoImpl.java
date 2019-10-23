package application.dao;

import application.entity.User;
import application.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    public JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO PEOPLE " +
                "VALUES (USER_ID_SEQUENCE.NEXTVAL,'" + user.getName() +
                "','" + user.getSurname() + "','" + user.getEmail() + "','" + user.getPassword() + "','" +
                user.getDateOfBirth() + "','" + user.getGender() + "','" + user.getBug() + "','" +
                user.getComment() + "')";
        jdbcTemplate.update(sql);
    }

    @Override
    public User logIn(String email, String password) {
        String sql = "SELECT * FROM PEOPLE WHERE EMAIL = ? AND PASSWORD = ?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), email, password);
    }



    @Override
    public void update(User user) {

    }

    @Override
    public void logOut() {

    }
}
