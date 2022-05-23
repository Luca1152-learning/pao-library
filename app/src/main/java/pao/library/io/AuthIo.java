package pao.library.io;

import pao.library.api.model.User;
import pao.library.api.service.UserService;

public class AuthIo {
    private static final UserService USER_SERVICE = new UserService();

    public User prompt() {
        System.out.println("Please sign in / sign up.");

//        try {
//            userService.signUp("username", "parola", "Prenume", "Nume");
//        } catch (SQLException exception) {
//            System.out.println("Sign up error. Try again!");
//        }

        User user = USER_SERVICE.signIn("usernamee", "parola");
        System.out.println(user.getFirstName());

        return null;
    }
}
