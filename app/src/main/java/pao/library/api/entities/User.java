package pao.library.api.entities;

public class User extends BaseEntity<Integer> {
    private final String username;
    private final String hashedPassword;
    private final String firstName;
    private final String lastName;

    public User(int id, String username, String hashedPassword, String firstName, String lastName) {
        super(id);

        this.username = username;
        this.hashedPassword = hashedPassword;
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "User[id=" + id + ", username=" + username + ", name=" + firstName + " " + lastName;
    }
}
