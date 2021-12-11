package com.example.projectjava;

public class ordo {
    int patient_ID;
    int commanderPar;
    int Medicament_ID;
    int Dose;
    int DureeJours;

    public int getPatient_ID() {
        return patient_ID;
    }

    public int getCommanderPar() {
        return commanderPar;
    }

    public int getMedicament_ID() {
        return Medicament_ID;
    }

    public int getDose() {
        return Dose;
    }

    public int getDureeJours() {
        return DureeJours;
    }

    public ordo(int patient_ID, int commanderPar, int medicament_ID, int dose, int dureeJours) {
        this.patient_ID = patient_ID;
        this.commanderPar = commanderPar;
        Medicament_ID = medicament_ID;
        Dose = dose;
        DureeJours = dureeJours;



    }
}
