package Erronka_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatuBaseKonexioa {

    private static final String URL = "jdbc:mysql://localhost:3306/Erronka_pakAG";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
