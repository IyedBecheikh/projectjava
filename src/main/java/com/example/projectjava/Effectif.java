package com.example.projectjava;

import java.util.Date;

public class Effectif {
    public String Nom;
    public String Prenom;
    public String CIN;
    public Date dateNaissance;
    public int Age;
    public String Adresse;
    public String Email;
    public int Sexe;
    public String Telephone;
    public String dateRecrutement;

    public Effectif(String nom, String prenom, String CIN, Date dateNaissance, int age, String adresse, String email, int sexe, String telephone, String dateRecrutement) {
        Nom = nom;
        Prenom = prenom;
        this.CIN = CIN;
        this.dateNaissance = dateNaissance;
        Age = age;
        Adresse = adresse;
        Email = email;
        Sexe = sexe;
        Telephone = telephone;
        this.dateRecrutement = dateRecrutement;
    }
}
