package com.example.tp2.moteur;

import java.util.Random;
public class JoueurRandom extends Joueur {
    public JoueurRandom(String nom){
        super(nom);
    }

    @Override
    public int jouer(int nbRestantes) {
        int rdm = new Random().nextInt(3) + 1;
        return rdm;
    }
}
