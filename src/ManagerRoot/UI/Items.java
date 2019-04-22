package ManagerRoot.UI;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Items {
    public static Button driBarItem(String text){
        Button button = new Button(text);


        return button;
    }

    public static VBox folder(String title, int type){
        VBox vBox = new VBox();

        vBox.setStyle("-fx-border-color: #bdc3c7; -fx-border-width: 3;");
        vBox.setAlignment(Pos.TOP_CENTER);


        FileInputStream input = null;
        try {
            if(type == 0) {
                input = new FileInputStream("src/ManagerRoot/UI/images/folder.png");
            }
            if(type == 1){
                input = new FileInputStream("src/ManagerRoot/UI/images/file.png");

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(90);
        imageView.setFitHeight(70);

        Label label = new Label();
        label.setText(title);
        label.setPrefWidth(90);
        label.setPrefHeight(20);
        label.setAlignment(Pos.CENTER);
        label.setTooltip(new Tooltip(title));

        vBox.getChildren().add(imageView);
        vBox.getChildren().add(label);

        vBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MainController.selectedItem.setStyle("-fx-border-color: #bdc3c7; -fx-border-width: 3;");
                MainController.selectedItem  = vBox;
                MainController.showSelectedItem();
            }
        });



        return vBox;
    }

}
