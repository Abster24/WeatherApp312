package cweatherapp.server;

import cweatherapp.server.sqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        String query = "SELECT id FROM users WHERE username = ?";
        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // User not found
    }

    public static List<String> getUserCities(int userId) {
        String query = "SELECT city_name FROM user_cities WHERE user_id = ?";
        List<String> cities = new ArrayList<>();
        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(resultSet.getString("city_name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static boolean addUserCity(String cityName) {
        String query = "INSERT INTO saved_cities (idUser, city1) VALUES (3, ?)";

        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            //statement.setInt(1, userId);
            statement.setString(1, cityName);
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

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // Return false if authentication fails
    }
}
}

