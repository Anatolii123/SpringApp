package application.dao;

import application.entity.User;
import application.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;

@Repository
public class UserDaoImpl implements UserDao {

    public JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        String pattern = "yyyy-MM-dd hh:mm:ss";
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("INSERT INTO PEOPLE ")
//                .append("");
        String sql = "INSERT INTO PEOPLE " +
                "VALUES (USER_ID_SEQUENCE.NEXTVAL,'" + user.getName() +
                "','" + user.getSurname() + "','" + user.getEmail() + "','" + user.getPassword() + "',TO_DATE('" +
                new SimpleDateFormat(pattern).format(user.getDateOfBirth()) + "', 'YYYY-MM-DD HH24:MI:SS'),'" + user.getGender() + "','" + user.getBug() + "','" +
                user.getComment() + "')";
        jdbcTemplate.update(sql);
    }

    @Override
    public User logIn(String email, String password) {
        String sql = "SELECT * FROM PEOPLE WHERE EMAIL = ? AND PASSWORD = ?";
        User user = new User();
        try {
            user = jdbcTemplate.queryForObject(sql, new UserMapper(), email, password);
        } catch (Exception e) {
            user = null;
        }
        return user;
    }



    @Override
    public void update(User user) {

    }

    @Override
    public void logOut() {

    }
}
