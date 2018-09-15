package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import sample.database.DatabaseHandler;
import sample.model.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField registerFirstName;

    @FXML
    private JFXTextField registerLastName;

    @FXML
    private JFXTextField registerUsername;

    @FXML
    private JFXPasswordField registerPassword;

    @FXML
    private JFXButton registerButton;

    @FXML
    void initialize() {
        registerButton.setOnAction(event -> {
            createUser();
        });
    }

    private void createUser(){
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String firstName = registerFirstName.getText();
        String lastName = registerLastName.getText();
        String username = registerUsername.getText();
        String password = registerPassword.getText();

        User user = new User(firstName, lastName, username, password);

        try {
            databaseHandler.registerUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
