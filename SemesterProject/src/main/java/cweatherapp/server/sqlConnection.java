package cweatherapp.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/weatherapp";
    private static final String DB_USER = "root"; // Replace with your username
    private static final String DB_PASSWORD = ""; // Replace with your password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
