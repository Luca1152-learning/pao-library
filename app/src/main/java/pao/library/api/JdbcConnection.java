package pao.library.api;

import pao.library.utils.Secrets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private static Connection connection = null;

    private JdbcConnection() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        Secrets.DATABASE_URL.getStr(), Secrets.DATABASE_USER.getStr(), Secrets.DATABASE_PASSWORD.getStr()
                );
            } catch (SQLException e) {
                System.err.println("Eroare la conectarea la baza de date.");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
