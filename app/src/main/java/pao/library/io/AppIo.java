package pao.library.io;

import pao.library.api.model.Role;
import pao.library.api.model.User;

import java.util.ArrayList;
import java.util.Arrays;

public class AppIo {
    private static final NumberedOptions USER_OPTIONS = new NumberedOptions(new ArrayList<>(Arrays.asList(
            "Browse books", // 1
            "Borrow book", // 2
            "Exit" // 3
    )));
    private static final NumberedOptions ADMIN_OPTIONS = new NumberedOptions(new ArrayList<>(Arrays.asList(
            "Browse books", // 1
            "Manage books", // 2
            "Manage borrows", // 3
            "Exit" // 4
    )));

    public void prompt() {
        // Authentication
        System.out.println("Welcome!\n");
        User user = (new AuthIo()).prompt();

        System.out.println("\nWelcome, " + user.getFirstName() + " " + user.getLastName() + "!");

        // Actual options
        if (user.getRole() == Role.USER) {
            promptNormalUserOptions();
        } else {
            promptAdminOptions();
        }
    }

    private void promptNormalUserOptions() {
        while (true) {
            int option = USER_OPTIONS.prompt();
            if (option == 1) {
                // Browse books
                (new BrowseBooksIo()).prompt();
            } else if (option == 2) {
                // Borrow book
                (new BorrowBookIo()).prompt();
            } else if (option == 3) {
                // Exit
                break;
            } else {
                throw new RuntimeException("Option not handled.");
            }
            System.out.println();
        }
    }

    private void promptAdminOptions() {
        while (true) {
            int option = ADMIN_OPTIONS.prompt();
            if (option == 1) {
                // Browse books
                (new BrowseBooksIo()).prompt();
            } else if (option == 2) {
                // Manage books
                (new ManageBooksIo()).prompt();
            } else if (option == 3) {
                // Manage borrows
                (new ManageBorrowsIo()).prompt();
            } else if (option == 4) {
                // Exit
                break;
            } else {
                throw new RuntimeException("Option not handled.");
            }
            System.out.println();
        }
    }
}
