package com.example.tp2.moteur;

public class JoueurIntelligent extends Joueur {
    public JoueurIntelligent(String nom){
        super(nom);
    }

    @Override
    public int jouer(int nbRestantes) {
        if((nbRestantes - 2) % 4 == 1 ) {
            return 2;
        }
        if((nbRestantes - 3) % 4 == 1 ) {
            return 3;
        }
        return 1;
    }
}
