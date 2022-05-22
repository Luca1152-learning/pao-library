package pao.library;

import pao.library.utils.Secrets;

import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        try {
            DriverManager.getConnection(Secrets.DATABASE_URL.getStr());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
