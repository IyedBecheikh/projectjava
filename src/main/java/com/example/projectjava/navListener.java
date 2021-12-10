package com.example.projectjava;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;

import static com.example.projectjava.Dashboard.addTab;

public class navListener implements ChangeListener {

    navListener(TabPane mainPane){
        new ChangeListener<TreeItem<String>>() {

            @Override
            public void changed(
                    ObservableValue<? extends TreeItem<String>> observable,
                    TreeItem<String> old_val, TreeItem<String> new_val) {
                TreeItem<String> selectedItem = new_val;

                switch (selectedItem.getValue()){
                    case "Accueil":
                        addTab("Accueil","Accueil.png",mainPane);
                        break;
                    case "Patients":
                        addTab("Patients","Patients.png",mainPane);
                        break;
                    case "Ordonnances":
                        addTab("Ordonnances","Ordonnances.png",mainPane);
                        break;
                    case "Rendez-vous / Examination":
                        addTab("Rendez-vous / Examination","RE.png",mainPane);
                        break;
                    case "Laboratoire":
                        addTab("Laboratoire","Laboratoire.png",mainPane);
                        break;
                    case "Imagerie":
                        addTab("Imagerie","Imagerie.png",mainPane);
                        break;
                    case "Hospitalisation":
                        addTab("Hospitalisation","Hospitalisation.png",mainPane);
                        break;
                    case "Bloc opératoire":
                        addTab("Bloc opératoire","BO.png",mainPane);
                        break;
                    case "Stock / Pharmacie":
                        addTab("Stock / Pharmacie","SP.png",mainPane);
                        break;
                    case "Comptes":
                        addTab("Comptes","Comptes.png",mainPane);
                        break;
                    case "Medecins":
                        addTab("Medecins","Medecins.png",mainPane);
                        break;
                    case "Paramedicals":
                        addTab("Paramedicals","Paramedicals.png",mainPane);
                        break;
                }

            }
        };
    };

    @Override
    public void changed(ObservableValue observableValue, Object o, Object t1) {

    }

}
