package com.example.projectjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class OrdonnancesTab implements Initializable {

    @FXML
    TextField fchercher;

    @FXML
    Button buttonChercher;

    @FXML
    TableView tv;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonChercher.setOnAction(this::chercher);

    }

    private void chercher(ActionEvent event) {
        ConnectionBD.buildList(fchercher.getText(),tv);
    }
}
