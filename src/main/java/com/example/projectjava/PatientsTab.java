package com.example.projectjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

public class PatientsTab implements Initializable {

    @FXML
    Button ajouterPatient;

    @FXML
    Button modiferPatient;

    @FXML
    TextField fnom;

    @FXML
    TextField fprenom;

    @FXML
    TextField fcin;

    @FXML
    TextField fage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ajouterPatient.setOnAction(this::addPatient);
        modiferPatient.setOnAction(this::afficherPatient);

    }

    private void afficherPatient(ActionEvent event) {
        Dashboard.addTab("Patients","Patients.png",Dashboard.corePane);
    }

    private void addPatient(ActionEvent event) {
        int execute = ConnectionBD.ajouterPatient(fnom.getText(),fprenom.getText(),fcin.getText(),"1990-05-19",9,1,"A+","Nabeul, Nabeul 8000","22197169","test@gmail.com");
        Alert alert;
        if (execute == 1){
            alert = Tools.buildInfo("Successful", "Patient ajouté avec succès!");
        } else {
            alert = Tools.buildError("Unsuccessful", "Erreur lors de l'ajout du patient", "303");
        }
        alert.showAndWait();
    }

}
