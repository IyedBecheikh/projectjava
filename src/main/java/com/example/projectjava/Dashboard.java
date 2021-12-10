package com.example.projectjava;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.control.TabPane.TabClosingPolicy.SELECTED_TAB;

public class Dashboard implements Initializable {

    @FXML
    TabPane mainPane;

    @FXML
    Label userLabel;

    @FXML
    TreeView navList;

    @FXML
    Label adminLabel;


    private static ImageView buildIcon(String icon) {
        String imgPatch;
        try {
            imgPatch = Dashboard.class.getResource(icon).toString();
        } catch (NullPointerException ex) {
            imgPatch = Dashboard.class.getResource("handIcon.png").toString();
        }
        ;

        Image i = new Image(imgPatch);
        ImageView imageView = new ImageView();
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        imageView.setImage(i);
        return imageView;
    }

    private static boolean checkTab(String id, TabPane pane) {
        Boolean exists = false;
        for (Tab tab : pane.getTabs()) {
            if (tab.getId() == null) {
                continue;
            } else if (tab.getId().equals(id)) {
                exists = true;
            }
        }
        return exists;
    }

    private static Tab findTab(String id, TabPane mainPane) {
        Tab foundTab = null;
        if (!checkTab(id, mainPane)) {
            return null;
        } else {
            for (Tab tab : mainPane.getTabs()) {
                if (tab.getId() == null) {
                    continue;
                } else if (tab.getId().equals(id)) {
                    foundTab = tab;
                    break;
                }
            }
        }
        return foundTab;
    }

    static void addTab(String name, String icon, TabPane pane) {
        if (!checkTab(name, pane)) {
            Tab newTab = new Tab(name);
            newTab.setGraphic(buildIcon(icon));
            newTab.setId(name);
            pane.getTabs().add(newTab);
            pane.getSelectionModel().select(newTab);
        } else {
            pane.getSelectionModel().select(findTab(name, pane));
        }
    }

/*
        int execute = ConnectionBD.ajouterMedecin("Iyed","Becheikh","09893923","1990-05-19",9,1,"29 Sidi Ali Somai Somaa, Nabeul 8023","27968610","iyedmoto1@gmail.com","Nutritionniste",0);

        Alert alert;
        if (execute == 1){
            alert = Tools.buildInfo("Successful", "Patient ajouté avec succès!");
        } else {
            alert = Tools.buildError("Unsuccessful", "Erreur lors de l'ajout du patient", "303");
        }
        alert.showAndWait();

        System.out.println(execute);*/


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adminLabel.setVisible(false);
        userLabel.setText(Login.effectif.Nom);

        mainPane.setTabClosingPolicy(SELECTED_TAB);
        TreeItem<String> newLeaf = new TreeItem<>("Main-Menu");
        navList.setRoot(newLeaf);
        List<TreeItem> leafs = new ArrayList<>();

        leafs.add(new TreeItem<>("Accueil", buildIcon("Accueil.png")));
        leafs.add(new TreeItem<>("Patients", buildIcon("Patients.png")));
        leafs.add(new TreeItem<>("Ordonnances", buildIcon("Ordonnances.png")));

        leafs.add(new TreeItem<>("Rendez-vous / Examination", buildIcon("RE.png")));
        leafs.add(new TreeItem<>("Laboratoire", buildIcon("Laboratoire.png")));
        leafs.add(new TreeItem<>("Imagerie", buildIcon("Imagerie.png")));

        leafs.add(new TreeItem<>("Hospitalisation", buildIcon("Hospitalisation.png")));
        leafs.add(new TreeItem<>("Bloc opératoire", buildIcon("BO.png")));
        leafs.add(new TreeItem<>("Stock / Pharmacie", buildIcon("SP.png")));

        navList.getRoot().getChildren().addAll(leafs);

        if (Login.isAdmin) {

            adminLabel.setVisible(true);

            TreeItem<String> adminLeaf = new TreeItem<>("Outils d'administration", buildIcon("Outils d'administration.png"));

            TreeItem<String> adminLeafy = new TreeItem<>("Comptes", buildIcon("Comptes.png"));
            TreeItem<String> adminLeafy2 = new TreeItem<>("Medecins", buildIcon("Medecins.png"));
            TreeItem<String> adminLeafy3 = new TreeItem<>("Paramedicals", buildIcon("Paramedicals.png"));

            adminLeaf.getChildren().addAll(adminLeafy, adminLeafy2, adminLeafy3);
            navList.getRoot().getChildren().add(adminLeaf);
        }

        navList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {

            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> old_val, TreeItem<String> new_val) {
                TreeItem<String> selectedItem = new_val;

                switch (selectedItem.getValue()) {
                    case "Accueil":
                        addTab("Accueil", "Accueil.png", mainPane);
                        break;
                    case "Patients":
                        addTab("Patients", "Patients.png", mainPane);
                        break;
                    case "Ordonnances":
                        addTab("Ordonnances", "Ordonnances.png", mainPane);
                        break;
                    case "Rendez-vous / Examination":
                        addTab("Rendez-vous / Examination", "RE.png", mainPane);
                        break;
                    case "Laboratoire":
                        addTab("Laboratoire", "Laboratoire.png", mainPane);
                        break;
                    case "Imagerie":
                        addTab("Imagerie", "Imagerie.png", mainPane);
                        break;
                    case "Hospitalisation":
                        addTab("Hospitalisation", "Hospitalisation.png", mainPane);
                        break;
                    case "Bloc opératoire":
                        addTab("Bloc opératoire", "BO.png", mainPane);
                        break;
                    case "Stock / Pharmacie":
                        addTab("Stock / Pharmacie", "SP.png", mainPane);
                        break;
                    case "Comptes":
                        addTab("Comptes", "Comptes.png", mainPane);
                        break;
                    case "Medecins":
                        addTab("Medecins", "Medecins.png", mainPane);
                        break;
                    case "Paramedicals":
                        addTab("Paramedicals", "Paramedicals.png", mainPane);
                        break;
                }

            }
        });

    }


}
