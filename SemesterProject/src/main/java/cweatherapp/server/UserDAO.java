package cweatherapp.server;

import cweatherapp.server.sqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDAO {

    public static boolean addUser(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password); // Consider hashing the password
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getUserId(String username) {
        String query = "SELECT userID FROM users WHERE username = ?";
        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("userID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static ArrayList<String> getUserCities(String name) {
        String query = "SELECT city FROM saved_cities WHERE userID = ?";
        ArrayList<String> cities = new ArrayList<>();
        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, getUserId(name));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(resultSet.getString("city"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static boolean addUserCity(String cityName, int userID) {
        String query = "INSERT INTO saved_cities (userID, city) VALUES (?, ?)";

        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userID);
            statement.setString(2, cityName);
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean login(String username, String password) {
        String query = "SELECT password FROM users WHERE username = ?";

        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Get the password from the database
                    String Password = resultSet.getString("password");
                    if (Objects.equals(password, Password)) {
                        return true;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // Return false if authentication fails
    }
}


