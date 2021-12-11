package com.example.projectjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AcceuilTab implements Initializable {

    @FXML
    Button ajouterButton;

    public void showPatient(ActionEvent event){
        Dashboard.addTab("AjouterPatients","AjouterPatients.png",Dashboard.corePane);

    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ajouterButton.setOnAction(this::showPatient);
    }
}
