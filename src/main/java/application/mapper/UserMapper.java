package application.mapper;

import application.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setDateOfBirth(resultSet.getDate("dateOfBirth"));
        user.setGender(resultSet.getString("gender"));
        user.setBug(resultSet.getString("bug"));
        user.setComment(resultSet.getString("comment"));
        return user;
    }
}
