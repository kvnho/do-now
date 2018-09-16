package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class ListController {

    @FXML
    private JFXListView<String> listTask;

    @FXML
    private JFXTextField listTaskField;

    @FXML
    private JFXTextField listDescriptionField;

    @FXML
    private JFXButton listSaveTaskButton;

    ObservableList<String> listView = FXCollections.observableArrayList("John", "Paul", "Kevin");

    @FXML
    void initialize() {
        listTask.setItems(listView);
        listTask.setCellFactory(param -> new JFXCell());
    }

    static class JFXCell extends JFXListCell<String>{
        HBox hBox = new HBox();
        Button helloButton = new Button("Hello");
        Label task = new Label();

        Pane pane = new Pane();
        Image icon = new Image("/sample/assets/icon_add.png");
        ImageView iconImg = new ImageView(icon);

        public JFXCell(){
            super();
            hBox.getChildren().addAll(iconImg, task, helloButton);
            hBox.setHgrow(pane, Priority.ALWAYS);
        }

        public void updateItem(String taskName, boolean empty){
            super.updateItem(taskName, empty);
            setText(null);
            setGraphic(null);

            if(taskName != null && !empty){
                task.setText(taskName);
                setGraphic(hBox);
            }
        }
    }

}
