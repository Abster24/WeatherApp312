package cweatherapp.ui;

import cweatherapp.api.Fetch;
import cweatherapp.server.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class AppController {
    @FXML private TextField cityInput;
    @FXML private Label weatherInfo;
    @FXML public Button Weatherbtn;
    @FXML public Button saveBtn;
    @FXML public Accordion savedCities;
    @FXML public Label savedSection;


    public void fetchWeather() {
        String city = cityInput.getText();

        try {
            // Test the function
            String[] weatherDetails = cweatherapp.api.Fetch.getWeatherDetails(city);
            weatherInfo.setText("City: " + weatherDetails[0] +
                    "\nCountry: " + weatherDetails[1] +
                    "\nTemperature: " + weatherDetails[2] +
                    "\nCurrently " + weatherDetails[3]);
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

    public void loginUser() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/loginfx.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 370, 175);
            Stage stage = new Stage();
            stage.setTitle("Log In");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


