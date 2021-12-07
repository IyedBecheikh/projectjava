package com.example.projectjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.TabPane.TabClosingPolicy.SELECTED_TAB;

public class Dashboard implements Initializable {

    @FXML
    Button test;

    @FXML
    TabPane mainPane;

    @FXML
    TreeView navList;

    private  ImageView buildIcon(String icon) {
        String imgPatch = Dashboard.class.getResource(icon).toString();
        Image i = new Image(imgPatch);
        ImageView imageView = new ImageView();
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        imageView.setImage(i);
        return imageView;
    }

    private boolean checkTab(String id,TabPane pane){
        Boolean exists = false;
        for (Tab tab:pane.getTabs()) {
            if (tab.getId() == null){continue;}
            else if (tab.getId().equals("WelcomeTab")){
                exists =  true;
            }
        }
        return exists;
    }

    private Tab findTab(String id){
        Tab foundTab = null;
        if(!checkTab(id,mainPane)){return null;}
        else{
            for (Tab tab:mainPane.getTabs()) {
                if (tab.getId() == null){continue;}
                else if (tab.getId().equals(id)){
                    foundTab = tab;
                    break;
                }
            }}
        return foundTab;
    }

    static void addTab(Tab tab, TabPane pane){
        pane.getTabs().add(tab);
    }

    private void justTesting(ActionEvent event){
        if (!checkTab("WelcomeTab",mainPane)){
            Tab newTab = new Tab("Welcome");
            newTab.setGraphic(buildIcon("handIcon.png"));
            newTab.setId("WelcomeTab");
            Dashboard.addTab(newTab,mainPane);
        }
        else {
            mainPane.getSelectionModel().select(findTab("WelcomeTab"));
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.setTabClosingPolicy(SELECTED_TAB);
        test.setOnAction(this::justTesting);
        TreeItem<String> newLeaf = new TreeItem<>("Main Menu");
        navList.setRoot(newLeaf);

        TreeItem<String> newLeaf2 = new TreeItem<>("Profil",buildIcon("profil.png"));
//        TreeItem<String> newLeaf3 = new TreeItem<>("Profil",buildIcon("profil.png"));
        navList.getRoot().getChildren().add(newLeaf2);
//        navList.getRoot().getChildren().add(newLeaf3);
    }



}
