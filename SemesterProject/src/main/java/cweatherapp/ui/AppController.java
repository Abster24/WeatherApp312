package cweatherapp.ui;

import cweatherapp.api.Fetch;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;


public class AppController {
    @FXML private TextField cityInput;
    @FXML private Label weatherInfo;

    @FXML public Button Weatherbtn;


    public void fetchWeather() {
        String city = cityInput.getText();

        try {
            // Test the function
            String[] weatherDetails = cweatherapp.api.Fetch.getWeatherDetails(city);
            /*System.out.println("City: " + weatherDetails[0]);
            System.out.println("Country: " + weatherDetails[1]);
            System.out.println("Temperature: " + weatherDetails[2]);
            System.out.println("Currently " + weatherDetails[3]);*/
            weatherInfo.setText("City: " + weatherDetails[0] +
                    "\nCountry: " + weatherDetails[1] +
                    "\nTemperature: " + weatherDetails[2] +
                    "\nCurrently " + weatherDetails[3]);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


