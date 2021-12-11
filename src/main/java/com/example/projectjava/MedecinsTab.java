package com.example.projectjava;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MedecinsTab implements Initializable{

        @FXML
        TableView medList;

        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    ConnectionBD.getAllDoctors(medList);
    }
}
