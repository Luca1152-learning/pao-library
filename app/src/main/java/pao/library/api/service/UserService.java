package pao.library.api.service;

import pao.library.api.dao.UserDao;
import pao.library.api.model.User;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserService {
    private static final UserDao USER_DAO = new UserDao();

    public static User signUp(String username, String password, String firstName, String lastName) throws SQLException {
        // Create the user object
        String hashedPassword = User.hashPassword(password);
        User user = new User(username, hashedPassword, firstName, lastName);

        // Save it to the database
        int id = USER_DAO.save(user);
        user.setId(id);

        return user;
    }

    public static User signIn(String username, String password) {
        // Get the hashed password
        String hashedPassword = User.hashPassword(password);

        // Filter all users by username and hashed password
        Collection<User> users = USER_DAO.getAll();
        List<User> filteredUsers = users.stream().filter(user ->
                Objects.equals(user.getUsername(), username) && Objects.equals(user.getHashedPassword(), hashedPassword)
        ).toList();

        if (filteredUsers.size() == 1) {
            return filteredUsers.get(0);
        } else {
            return null;
        }
    }
}
