package ManagerRoot;

import ManagerRoot.UI.DefStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        System.out.println(System.getProperty("user.dir"));

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DefStage.mainStage = primaryStage;

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("UI/main.fxml"))));
        primaryStage.setTitle("File Manager");

        primaryStage.show();
    }
}
