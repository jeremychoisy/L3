package com.example.tp2.moteur;

public class FabriqueDeJoueur {
    public enum TYPE_DE_JOUEUR { BASIC, RANDOM, INTELLIGENT, TANGIBLE, TACTILE}
    public static Joueur obtenirUnJoueur(TYPE_DE_JOUEUR type, String nom) {
        Joueur résultat;
        switch (type) {
            case INTELLIGENT:
                résultat = new JoueurIntelligent(nom);
                break;
            case RANDOM:
                résultat = new JoueurRandom(nom);
                break;
            default:
                résultat = new Joueur(nom);
        }
        return résultat;
    }
}
