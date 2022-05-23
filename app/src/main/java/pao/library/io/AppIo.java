package pao.library.io;

import pao.library.api.model.User;

public class AppIo {
    public void prompt() {
        // Authentication
        System.out.println("Welcome!\n");
        User user = (new AuthIo()).prompt();

        System.out.println("\nWelcome, " + user.getFirstName() + " " + user.getLastName() + "!");
    }
}
