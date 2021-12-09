package com.example.projectjava;

import javafx.scene.control.Alert;

public class Tools {

    // Fonction qui retourn un dialog d'erreur (Alert)
    public static Alert buildError(String title,String Header,String ContentText) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(Header);
        alert.setContentText("Error State: "+ ContentText);

        return alert;
    }

    // Fonction qui retourn un dialog d'information (Alert)
    public static Alert buildInfo(String title,String ContentText) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(ContentText);

        return alert;
    }
}
