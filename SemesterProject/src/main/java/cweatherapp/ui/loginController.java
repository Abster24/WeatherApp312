package cweatherapp.ui;

import cweatherapp.server.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.ActionEvent;


public class loginController {
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


    public void createUser() {
        String name = nameField.getText();
        String password = passwordField.getText();

        UserDAO.addUser(name, password);

    }


}
