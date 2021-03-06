package com.example.projectjava;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.Calendar;
import java.util.List;

// 1 - Successful
// 0 - Error

public class ConnectionBD {

    // Fonction qui retourn la connecetion a la BD (Coonection)
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/clinique?" +
                            "user=root");
        }

        catch (SQLException ex){
            Alert error = Tools.buildError("Error",ex.getMessage(), ex.getSQLState());
            error.showAndWait();
        }
        return conn;
    }

    // Fonction qui sert a ajouter un patient a la BD
    public static int ajouterPatient(String Nom, String Prenom, String CIN, String dateNaissance, int Age, int Sexe, String groupeSanguin, String Adresse, String Telephone, String Email){

        try {
            Date parsedDate =  java.sql.Date.valueOf(dateNaissance);

            Connection con = getConnection();
            Statement st = con.createStatement();

            String sql = String.format("INSERT INTO `patient` (`Nom`, `Prenom`, `CIN`, `dateNaissance`, `Age`, `Sexe`, `groupeSanguin`, `Adresse`, `Telephone`, `Email`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",Nom,Prenom,CIN,parsedDate,Age,Sexe,groupeSanguin,Adresse,Telephone,Email);

            st.executeUpdate(sql);

            return 1;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    // Fonction qui sert a ajouter un effectif a la BD
    public static int ajouterEffectif(String Nom, String Prenom, String CIN, String dateNaissance, int Age, int Sexe, String Adresse, String Telephone, String Email) {

        try {
            Date parsedDate =  java.sql.Date.valueOf(dateNaissance);
            Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            Connection con = getConnection();
            Statement st = con.createStatement();

            String sql = String.format("INSERT INTO `effectif` (`Nom`, `Prenom`, `CIN`, `dateNaissance`, `Age`, `Sexe`, `Adresse`, `Telephone`, `Email`, `dateRecrutement`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",Nom,Prenom,CIN,parsedDate,Age,Sexe,Adresse,Telephone,Email,now);

            st.executeUpdate(sql);

            return 1;
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            return 0;
        }
    }


    // Fonction qui sert a ajouter un medecin a la BD
    public static int ajouterMedecin(String Nom, String Prenom, String CIN, String dateNaissance, int Age, int Sexe, String Adresse, String Telephone, String Email,String Specialite,int Departement){

        try {
            int execute = ajouterEffectif(Nom, Prenom, CIN, dateNaissance, Age, Sexe, Adresse, Telephone, Email);

            if(execute==1){
                Connection con = getConnection();
                Statement st = con.createStatement();

                String sql = "SELECT ID FROM effectif WHERE CIN = '" + CIN + "';";
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    String id = rs.getString("ID");

                    Statement st2 = con.createStatement();
                    String sql2 = String.format("INSERT INTO `medecin` (`eff_ID`, `Specialite`, `Depatment`) VALUES ('%s', '%s', '%s')",id,Specialite,Departement);

                    st2.executeUpdate(sql2);

                }

                return 1;
            }
            else{
                return 0;
            }
        }
        catch (SQLException ex){
            return 0;
        }
    }

    // Fonction qui sert a ajouter un medecin a la BD
    public static int ajouterparamedical(String Nom, String Prenom, String CIN, String dateNaissance, int Age, int Sexe, String Adresse, String Telephone, String Email,String Profession,int Poste){

        try {
            int execute = ajouterEffectif(Nom, Prenom, CIN, dateNaissance, Age, Sexe, Adresse, Telephone, Email);

            if(execute==1){
                Connection con = getConnection();
                Statement st = con.createStatement();

                String sql = "SELECT ID FROM effectif WHERE CIN = '" + CIN + "';";
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    String id = rs.getString("ID");

                    Statement st2 = con.createStatement();
                    String sql2 = String.format("INSERT INTO `paramedical` (`eff_ID`, `Profession`, `Poste`) VALUES ('%s', '%s', '%s')",id,Profession,Poste);

                    st2.executeUpdate(sql2);

                }

                return 1;
            }
            else{
                return 0;
            }
        }
        catch (SQLException ex){
            return 0;
        }
    }

    public static boolean checkLogin(String email, String pass) {
        try {
            Connection con = getConnection();
            Statement st = con.createStatement();

            String sql = String.format("SELECT * FROM `compte` WHERE `Email` = '%s' AND Password = '%s';",email,pass);
            ResultSet rs = st.executeQuery(sql);
            if (!rs.isBeforeFirst() ) {
                return false;
            }
            else {
                while (rs.next()) {
                    Login.isLoggedIn = true;
                    Login.IDeff = rs.getInt("IDeff");
                    Login.isAdmin = rs.getBoolean("isAdmin");
                    Login.effectif = getEffectif(Login.IDeff);
                }
                return true;
            }
        }
        catch (SQLException ex){
        return false;
        }
    }

    public static Effectif getEffectif(int IDeff) {
        try {
            Connection con = getConnection();
            Statement st = con.createStatement();

            String sql = String.format("SELECT * FROM `effectif` WHERE `ID` = '%s';",IDeff);
            ResultSet rs = st.executeQuery(sql);

            if (!rs.isBeforeFirst() ) {
                Alert error = Tools.buildError("Error","No effectif found","303");
                error.showAndWait();
                return null;
            }
            else {
                Effectif eff = null;
                while (rs.next()) {
                    String Nom = rs.getString("Nom");
                    String Prenom = rs.getString("Prenom");
                    String CIN = rs.getString("CIN");
                    java.util.Date dateNaissance = rs.getDate("dateNaissance");
                    int Age = rs.getInt("Age");
                    String Adresse = rs.getString("Adresse");
                    String Email = rs.getString("Email");
                    int Sexe = rs.getInt("Sexe");
                    String Telephone = rs.getString("Telephone");
                    String dateRecrutement = rs.getString("dateRecrutement");

                    eff = new Effectif(Nom, Prenom, CIN, dateNaissance, Age, Adresse, Email, Sexe, Telephone, dateRecrutement);
                }
                return eff;
            }

        } catch (SQLException ex) {
            Alert error = Tools.buildError("Error", ex.getMessage(), ex.getSQLState());
            error.showAndWait();
            return null;
        }
    }

    public static void getAllDoctors(TableView list) {
        try {

            TableColumn nameColumn = new TableColumn("Nom");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("Nom"));

            TableColumn PrenomColumn = new TableColumn("Prenom");
            PrenomColumn.setCellValueFactory(new PropertyValueFactory<>("Prenom"));

            TableColumn CINColumn = new TableColumn("CIN");
            CINColumn.setCellValueFactory(new PropertyValueFactory<>("CIN"));

            TableColumn AgeColumn = new TableColumn("Age");
            AgeColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));

            TableColumn EmailColumn = new TableColumn("Email");
            EmailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));

            TableColumn TelephoneColumn = new TableColumn("Telephone");
            TelephoneColumn.setCellValueFactory(new PropertyValueFactory<>("Telephone"));

            TableColumn AdresseColumn = new TableColumn("Adresse");
            AdresseColumn.setCellValueFactory(new PropertyValueFactory<>("Adresse"));

            list.getColumns().addAll(nameColumn,PrenomColumn,CINColumn,AgeColumn,EmailColumn,TelephoneColumn,AdresseColumn);


            Connection con = getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT * FROM effectif INNER JOIN medecin ON effectif.ID = medecin.`eff_ID`;";
            ResultSet rs = st.executeQuery(sql);

            if (!rs.isBeforeFirst() ) {
                Alert error = Tools.buildError("Error","No medecin found","303");
                error.showAndWait();
            }
            else {
                while (rs.next()) {
                    String Nom = rs.getString("Nom");
                    String Prenom = rs.getString("Prenom");
                    String CIN = rs.getString("CIN");
                    java.util.Date dateNaissance = rs.getDate("dateNaissance");
                    int Age = rs.getInt("Age");
                    String Adresse = rs.getString("Adresse");
                    String Email = rs.getString("Email");
                    int Sexe = rs.getInt("Sexe");
                    String Telephone = rs.getString("Telephone");
                    String dateRecrutement = rs.getString("dateRecrutement");

                    Effectif eff = new Effectif(Nom,Prenom,CIN,dateNaissance,Age,Adresse,Email,Sexe,Telephone,dateRecrutement);
                    list.getItems().add(eff);
                }
            }

        } catch (SQLException ex) {
            Alert error = Tools.buildError("Error", ex.getMessage(), ex.getSQLState());
            error.showAndWait();
        }
    }

    public static void buildList(String text, TableView tv) {
    try {
        Connection con = getConnection();
        Statement st = con.createStatement();

        String sql = String.format("SELECT * FROM `ordonnance` WHERE `patient_ID` IN (SELECT ID FROM `patient` WHERE `Nom`='%s');",text);
        ResultSet rs = st.executeQuery(sql);

        if (rs.isBeforeFirst()){
            while (rs.next()) {
                int patient_ID = rs.getInt("patient_ID");
                int commanderPar = rs.getInt("commanderPar");
                int Medicament_ID = rs.getInt("Medicament_ID");
                int Dose = rs.getInt("Dose");
                int DureeJours = rs.getInt("DureeJours");

                TableColumn patient_IDColumn = new TableColumn("patient_ID");
                patient_IDColumn.setCellValueFactory(new PropertyValueFactory<>("patient_ID"));

                TableColumn commanderParColumn = new TableColumn("commanderPar");
                commanderParColumn.setCellValueFactory(new PropertyValueFactory<>("commanderPar"));

                TableColumn Medicament_IDColumn = new TableColumn("Medicament_ID");
                Medicament_IDColumn .setCellValueFactory(new PropertyValueFactory<>("Medicament_ID"));

                TableColumn DoseColumn = new TableColumn("Dose");
                DoseColumn.setCellValueFactory(new PropertyValueFactory<>("Dose"));

                TableColumn DureeJoursColumn = new TableColumn("DureeJours");
                DureeJoursColumn.setCellValueFactory(new PropertyValueFactory<>("DureeJours"));


                tv.getColumns().addAll(patient_IDColumn,commanderParColumn,Medicament_IDColumn,DoseColumn,DureeJoursColumn);

                ordo eff = new ordo(patient_ID,commanderPar,Medicament_ID,Dose,DureeJours);
                tv.getItems().add(eff);
            }
        }

    } catch (SQLException ex) {
        Alert error = Tools.buildError("Error", ex.getMessage(), ex.getSQLState());
        error.showAndWait();
    }


    }
}
