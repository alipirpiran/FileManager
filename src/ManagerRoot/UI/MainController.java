package ManagerRoot.UI;

import ManagerRoot.Datas;
import ManagerRoot.Manager.Manager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import sun.security.krb5.internal.crypto.Des;


import javax.xml.crypto.Data;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainController {
    boolean showHiddenFiles = false;

    @FXML
    private TilePane mainItemsPane;

    @FXML
    HBox itemBar;

    @FXML
    private CheckBox hiddenChoice;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private HBox hboxInScrollPane;

    static Path currentPath = Manager.currentPath();
    static ArrayList<LocalItems> items = new ArrayList<>();

    static VBox selectedItem = new VBox();

    public void initialize() {
        DefStage.mainStage.widthProperty().addListener((obs, oldval, newval) -> {
            mainItemsPane.setPrefWidth(newval.doubleValue());
            scrollPane.setPrefWidth(newval.doubleValue());
        });
        DefStage.mainStage.heightProperty().addListener((obs, oldval, newval) -> {
            scrollPane.setPrefHeight(newval.doubleValue());
        });

        refreshItemBar();
    }

    private void goToPath(Path path1) {
        if(Files.isDirectory(path1))
            currentPath = path1;
        else {
            try {
                String command = "see " + "'" + path1 + "'";
                String[] args = new String[] {"/bin/bash", "-c", command, "with", "args"};
                java.lang.Process proc = new ProcessBuilder(args).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        refreshItemBar();
        refreshMainItemsPane();

    }

    public void refreshItemBar() {
        ArrayList dir = new ArrayList();
        Path path = currentPath;
        int len = path.toAbsolutePath().getNameCount();


        boolean addedRoot = false;
        for(int i=0; i<len + 1; i++){
            ButtonHandle buttonHandle = new ButtonHandle();
            Button btn = Items.driBarItem(path.getFileName().toString());

            Path pth = path;
            btn.setOnAction(new EventHandler<ActionEvent>() {
                 @Override
                 public void handle(ActionEvent event) {
                    goToPath(pth);
                 }
             });

            buttonHandle.button = btn;
            buttonHandle.path = path;

            Datas.AddDir(buttonHandle);
            dir.add(btn);

            path = path.toAbsolutePath().getParent();
            if(path.getFileName() == null) {
                break;
            }

        }

        itemBar.getChildren().clear();
        itemBar.getChildren().setAll(Process.reversList(dir));
    }

    public void refreshMainItemsPane() {
        mainItemsPane.getChildren().clear();
        refreshItemsArratList();

        items = Process.sort(items,Sort.Name);
        mainItemsPane.getChildren().addAll(getItems());

    }

    public ArrayList getItems(){
        ArrayList localItems = new ArrayList();

        for(LocalItems item:items){
            VBox vBox = null;

            switch (item.type){
                case 0:
                    vBox = Items.folder(item.path.toAbsolutePath().getFileName().toString(), 0);
                    break;

                case 1:
                    vBox = Items.folder(item.path.toAbsolutePath().getFileName().toString(), 1);
                    break;

            }

            VBox finalVbox = vBox;
            finalVbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    MainController.selectedItem.setStyle("-fx-border-color: #bdc3c7; -fx-border-width: 3;");
                    MainController.selectedItem  = finalVbox;
                    MainController.showSelectedItem();

                    if(event.getClickCount() == 2){
                        goToPath(item.path);
                    }
                }
            });

            if(!showHiddenFiles){
                if (((Label)finalVbox.getChildren().get(1)).getText().charAt(0) == '.') {
                    continue;
                }
            }

            localItems.add(finalVbox);
        }
        return localItems;
    }

    public void refreshItemsArratList(){
        items.clear();

        File file = new File(currentPath.toString());

        for(File f:file.listFiles()){
            LocalItems localItems = new LocalItems();
            if(f.isFile()){
                localItems.path = f.toPath();
                localItems.type = 1;
                items.add(localItems);
            }else {
                localItems.path = f.toPath();
                localItems.type = 0;
                items.add(localItems);
            }


        }
    }

    public static void showSelectedItem(){
        selectedItem.setStyle("-fx-background-color: #3498db");
    }

    @FXML
    void hidden(ActionEvent event) {
        if (hiddenChoice.isSelected()) {
            showHiddenFiles = true;
        }else {
            showHiddenFiles = false;
        }

        refreshMainItemsPane();
    }

}

class LocalItems{
    Path path;
    Integer type;

}
