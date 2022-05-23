package pao.library.io;

import pao.library.api.model.User;

public class AppIo {
    public void prompt() {
        System.out.println("Welcome!\n");
        User user = (new AuthIo()).prompt();
    }
}
