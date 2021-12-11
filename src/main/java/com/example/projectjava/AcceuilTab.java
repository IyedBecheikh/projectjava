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

    @FXML
    Button ordoButton;

    @FXML
    Button medButton;

    @FXML
    Button phButton;

    @FXML
    Button comptesButton;

    @FXML
    Button rvButton;


    public void showPatient(ActionEvent event){
        Dashboard.addTab("AjouterPatients","AjouterPatients.png",Dashboard.corePane);

    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ajouterButton.setOnAction(this::showPatient);
        ordoButton.setOnAction(this::buildOrdo);
        medButton.setOnAction(this::medButton);
        phButton.setOnAction(this::phButton);
        comptesButton.setOnAction(this::comptesButton);
        rvButton.setOnAction(this::rvButton);
    }

    private void rvButton(ActionEvent event) {
        Dashboard.addTab("Rendez-vous / Examination","RE.png",Dashboard.corePane);
    }

    private void comptesButton(ActionEvent event) {
        Dashboard.addTab("Comptes","Comptes.png",Dashboard.corePane);
    }

    private void phButton(ActionEvent event) {
        Dashboard.addTab("Stock / Pharmacie","SP.png",Dashboard.corePane);
    }

    private void medButton(ActionEvent event) {
        Dashboard.addTab("Medecins","Medecins.png",Dashboard.corePane);
    }

    private void buildOrdo(ActionEvent event) {
        Dashboard.addTab("Ordonnances","Ordonnances.png",Dashboard.corePane);
    }

}
