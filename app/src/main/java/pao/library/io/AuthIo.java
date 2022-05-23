package pao.library.io;

import pao.library.api.model.User;
import pao.library.api.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AuthIo {
    private static final NumberedOptions AUTH_OPTIONS = new NumberedOptions(new ArrayList<>(Arrays.asList(
            "Sign up", // 1
            "Sign in" // 2
    )));
    private static final UserService USER_SERVICE = new UserService();

    public User prompt() {
        System.out.println("Please sign in / sign up.");

        int option = AUTH_OPTIONS.prompt();
        if (option == 1) {
            // Sign up
            while (true) {
                System.out.println();
                String username = promptUsername();
                String password = promptPassword();
                String firstName = promptFirstName();
                String lastName = promptLastName();
                try {
                    return USER_SERVICE.signUp(username, password, firstName, lastName);
                } catch (SQLException exception) {
                    // Probably there's a duplicate username
                    System.out.println("\nSign up error. Try again.");
                }
            }
        } else if (option == 2) {
            // Sign in
            while (true) {
                System.out.println();
                String username = promptUsername();
                String password = promptPassword();
                User user = USER_SERVICE.signIn(username, password);
                if (user != null) {
                    return user;
                } else {
                    System.out.println("\nBad credentials. Try again.");
                }
            }
        } else {
            throw new RuntimeException("Option not handled.");
        }
    }

    private String promptUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        return scanner.next();
    }

    private String promptPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Password: ");
        return scanner.next();
    }

    private String promptFirstName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("First name: ");
        return scanner.next();
    }

    private String promptLastName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Last name: ");
        return scanner.next();
    }
}
