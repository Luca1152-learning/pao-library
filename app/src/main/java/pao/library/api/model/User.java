package pao.library.api.model;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class User extends BaseEntity<Integer> {
    private final String username;
    private final String hashedPassword;
    private final String firstName;
    private final String lastName;
    private final Role role;

    public User(int id, String username, String hashedPassword, String firstName, String lastName, Role role) {
        super(id);

        this.username = username;
        this.hashedPassword = hashedPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public User(String username, String hashedPassword, String firstName, String lastName) {
        super(null);
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = Role.USER;
    }

    public static String hashPassword(String password) {
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User[id=" + id + ", username=" + username + ", name=" + firstName + " " + lastName + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) && hashedPassword.equals(user.hashedPassword) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, hashedPassword, firstName, lastName, role);
    }
}
