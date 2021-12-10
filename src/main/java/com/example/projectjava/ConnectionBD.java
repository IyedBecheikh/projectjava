package com.example.projectjava;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.Calendar;

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
}
