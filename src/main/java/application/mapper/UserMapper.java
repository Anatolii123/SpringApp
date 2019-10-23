package application.mapper;

import application.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("ID"));
        user.setName(resultSet.getString("NAME"));
        user.setSurname(resultSet.getString("SURNAME"));
        user.setEmail(resultSet.getString("EMAIL"));
        user.setPassword(resultSet.getString("PASSWORD"));
        user.setDateOfBirth(resultSet.getDate("DATE_OF_BIRTH"));
        user.setGender(resultSet.getString("GENDER"));
        user.setBug(resultSet.getString("BUG"));
        user.setComment(resultSet.getString("COMMENTS"));
        return user;
    }
}
