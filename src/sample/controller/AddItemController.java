package sample.controller;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.animations.Shaker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController {
    public static int userId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ImageView addButton;

    @FXML
    private Label notTaskLabel;

    @FXML
    void initialize() {
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker shake = new Shaker(addButton);
            shake.shake();

            addButton.relocate(0,0);
            notTaskLabel.relocate(0, 0);
            addButton.setOpacity(0);
            notTaskLabel.setOpacity(0);

            try {
                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/view/addItemForm.fxml"));


                AddItemFormController.userId = getUserId();
                //AddItemFormController addItemFormController = new AddItemFormController();
                //addItemFormController.setUserId(getUserId());


                rootAnchorPane.getChildren().setAll(formPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId(){
        return this.userId;
    }
}
