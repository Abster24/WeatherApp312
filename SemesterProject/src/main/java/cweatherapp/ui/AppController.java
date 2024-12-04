package cweatherapp.ui;

import cweatherapp.api.Fetch;
import cweatherapp.server.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static cweatherapp.server.UserDAO.getUserCities;


public class AppController {
    @FXML public AnchorPane mainPage;
    @FXML private TextField cityInput;
    @FXML private Label weatherInfo;
    @FXML public Button Weatherbtn;
    @FXML public Button saveBtn;
    @FXML public TextArea savedCities;
    @FXML
    public Pane loginPane;
    @FXML
    private TextField nameField;
    @FXML
    private TextField passwordField;
    @FXML
    public Button loginBtn;
    @FXML
    public Button createBtn;
    @FXML
    public Label alert;


    public void fetchWeather() {
        String city = cityInput.getText();
        try {
            String[] weatherDetails = cweatherapp.api.Fetch.getWeatherDetails(city);
            weatherInfo.setText("City: " + weatherDetails[0] +
                    "\nState: " + weatherDetails[1] +
                    "\nCountry: " + weatherDetails[2] +
                    "\nTemperature: " + weatherDetails[3] +
                    "\nCurrently " + weatherDetails[4]);
            saveBtn.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveCity() {
        String city = cityInput.getText();
        try {
            sqlConnection.getConnection();
            UserDAO.addUserCity(city);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void createUser() {
        String name = nameField.getText();
        String password = passwordField.getText();

        try {
            UserDAO.addUser(name, password);
            alert.setVisible(true);
            alert.setText("User added successfully");
        } catch (Exception e) {
            alert.setVisible(true);
            alert.setText("User not added");
            e.printStackTrace();
        }
    }

    public void showLogin() {
        loginPane.setVisible(true);
    }

    public void loginUser() {
        String name = nameField.getText();
        String password = passwordField.getText();

        boolean successful = UserDAO.login(name,password);
        if (successful) {
            alert.setText("Login successful");
            loginPane.setVisible(false);
            savedCities.setVisible(true);
            try {
                List<String> cities = List.of(Fetch.getWeatherDetails(getUserCities(name).toString()));
                savedCities.setText(cities.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            alert.setText("login failed");
            alert.setVisible(true);
        }
    }

}


