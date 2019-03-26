package com.example.tp2.moteur;

import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class JeuxDesAllumettes {
    private ArrayList<Joueur> participants;
    private Joueur gagnant;
    private Joueur joueurCourant;

    private int nbTotalAllumettes = 21;
    private int nbJoueurs = 2;

    private int nbAllumettesRestantes;
    private boolean state;

    public JeuxDesAllumettes() {
        participants = new ArrayList<Joueur>();
        réinitialiser();
    }
    // pour réinitialiser et (re)commencer une partie
    public void réinitialiser(){
        nbAllumettesRestantes = nbTotalAllumettes;
        gagnant = null;
    }
    // pour ajouter un joueur à la partie
    public boolean ajouterJoueur(Joueur j){
        participants.add(j);
        return true;
    }
    // pour savoir s’il y a suffisamment de joueurs à la partie
    public boolean partieComplete(){
        if(participants.size() == nbJoueurs){
            return true;
        }
        return false;
    }
    // pour savoir le nombre initial d’allumettes
    public int obtenirNbTotalAllumettes(){
        return nbTotalAllumettes;
    }

    // pour savoir combien il en reste
    public int obtenirNbAllumettesRestantes(){
        return nbAllumettesRestantes;
    }

    public void setNbAllumettesRestantes(int nbAllumettesRestantes) {
        this.nbAllumettesRestantes = nbAllumettesRestantes;
    }

    // pour passer le jeu dans un état « démarrer »
    public void démarrer(boolean state){
        this.state = state;
    }

    public ArrayList<Joueur> getParticipants() {
        return participants;
    }

    // pour savoir à qui c’est de jouer
    public Joueur aQuiDeJouer(){
        if(joueurCourant.equals(participants.get(0))){
            joueurCourant = participants.get(1);
            return joueurCourant;
        } else {
            joueurCourant = participants.get(0);
            return joueurCourant;
        }
    }
    // pour faire jouer le joueur courant, déterminer s’il y a un gagnant, sinon changer de joueur courant. Retourne le nombre d’allumettes prises
    public int jouerUnTour() {
        int nbAllumettesPrises = joueurCourant.jouer(nbAllumettesRestantes);
        switch (nbAllumettesRestantes - nbAllumettesPrises) {
            case 0:
                gagnant = aQuiDeJouer();
                break;
            case 1:
                gagnant = joueurCourant;
                break;
            default:
                if(nbAllumettesRestantes - nbAllumettesPrises < 0){
                    gagnant = aQuiDeJouer();
                }
        }
        return nbAllumettesPrises;
    }

    // pour savoir qui est le gagnant. Retourne null s’il n’y en a pas (encore)
    public Joueur obtenirGagnant(){
        return gagnant;
    }

    public Joueur getJoueurCourant(){
        return joueurCourant;
    }

    public void setPremierJoueur(int index){
        this.joueurCourant = participants.get(index);
    }

    public int getNbTotalAllumettes() {
        return nbTotalAllumettes;
    }

    public void setNbTotalAllumettes(int nbTotalAllumettes) {
        this.nbTotalAllumettes = nbTotalAllumettes;
    }
}