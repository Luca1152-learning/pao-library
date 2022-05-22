package pao.library;

import pao.library.api.dao.Dao;
import pao.library.api.dao.UserDao;
import pao.library.api.model.User;

public class App {
    private static final Dao<User, Integer> USER_DAO = new UserDao();

    public static void main(String[] args) {
        User user = USER_DAO.get(1);
        System.out.println(user.getUsername());
    }
}
