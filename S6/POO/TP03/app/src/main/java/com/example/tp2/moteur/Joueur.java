package com.example.tp2.moteur;

public class Joueur {
    private String nom;

    public Joueur(String nom){
        this.nom = nom;
    }

    // pour affecter un nom au joueur
    public void changerNom(String n){
        nom = n;
    }
    // pour savoir son nom
    public String obtenirNom(){
        return nom;
    }
    public String toString(){
        return "" + nom;
    }
    // pour faire jouer ce joueur. Par défaut, cela peut être 1.
    public int jouer(int nbRestantes){
        return 1;
    }
    // pour faire une petite attente, méthode utilisée par le contrôleur
    public void temporiser() throws InterruptedException{
        Thread.sleep(2000);
    }
}
