package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.model.Task;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddItemFormController {
    public static int userId;
    private DatabaseHandler databaseHandler;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField taskField;

    @FXML
    private JFXTextField descriptionField;

    @FXML
    private JFXButton saveTaskButton;

    @FXML
    private Label successLabel;

    @FXML
    private JFXButton tasksButton;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();

        saveTaskButton.setOnAction(event -> {
            Task task = new Task();

            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTimeInMillis());

            String taskText = taskField.getText().trim();
            String taskDescription = descriptionField.getText().trim();

            if(taskText.equals("") || taskDescription.equals("")){
                System.out.println("Nothing added");
            }
            else{
                task.setUserId(AddItemController.userId);
                task.setTask(taskText);
                task.setDateCreated(timestamp);
                task.setDescription(taskDescription);

                try {
                    databaseHandler.insertTask(task);
                    successLabel.setVisible(true);
                    tasksButton.setVisible(true);

                    taskField.setText("");
                    descriptionField.setText("");

                    tasksButton.setOnAction(event1 -> {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/sample/view/list.fxml"));

                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.showAndWait();
                    });
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
