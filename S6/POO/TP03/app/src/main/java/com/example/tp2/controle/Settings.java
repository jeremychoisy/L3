package com.example.tp2.controle;

import android.content.Context;
import android.content.Intent;

import com.example.tp2.SettingsActivity;
import com.example.tp2.moteur.FabriqueDeJoueur;

public class Settings {
    private FabriqueDeJoueur.TYPE_DE_JOUEUR type1;
    private FabriqueDeJoueur.TYPE_DE_JOUEUR type2;

    private String nomJoueur1;
    private String nomJoueur2;

    private int nbAllumettes;

    private boolean premierJoueur;


    public Settings() {
        this.nomJoueur1 = "Guest_1";
        this.nomJoueur2 = "Guest_2";

        this.type1 = FabriqueDeJoueur.TYPE_DE_JOUEUR.BASIC;
        this.type2 = FabriqueDeJoueur.TYPE_DE_JOUEUR.BASIC;

        this.nbAllumettes = 21;

        this.premierJoueur = true;
    }

    public Intent getSettings(Context c) {
        return new Intent(c, SettingsActivity.class);
    }

    public boolean get(Intent data) {
        String nomJoueur1 = data.getStringExtra("nomJoueur1");
        String nomJoueur2 = data.getStringExtra("nomJoueur2");

        String type1 = data.getStringExtra("type1");
        String type2 = data.getStringExtra("type2");

        int nbAllumettes = data.getIntExtra("nbAllumettes",21);
        return true;
    }

    public FabriqueDeJoueur.TYPE_DE_JOUEUR getType1() {
        return type1;
    }

    public void setType1(FabriqueDeJoueur.TYPE_DE_JOUEUR type1) {
        this.type1 = type1;
    }

    public FabriqueDeJoueur.TYPE_DE_JOUEUR getType2() {
        return type2;
    }

    public void setType2(FabriqueDeJoueur.TYPE_DE_JOUEUR type2) {
        this.type2 = type2;
    }

    public String getNomJoueur1() {
        return nomJoueur1;
    }

    public void setNomJoueur1(String nomJoueur1) {
        this.nomJoueur1 = nomJoueur1;
    }

    public String getNomJoueur2() {
        return nomJoueur2;
    }

    public void setNomJoueur2(String nomJoueur2) {
        this.nomJoueur2 = nomJoueur2;
    }

    public int getNbAllumettes() {
        return nbAllumettes;
    }

    public void setNbAllumettes(int nbAllumettes) {
        this.nbAllumettes = nbAllumettes;
    }

    public boolean isPremierJoueur() {
        return premierJoueur;
    }

    public void setPremierJoueur(boolean premierJoueur) {
        this.premierJoueur = premierJoueur;
    }
}