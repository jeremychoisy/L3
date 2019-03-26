package com.example.tp2.joueurHumain;

import com.example.tp2.moteur.Joueur;

public class JoueurHumain extends Joueur {
    private InteractionHumaine I;

    public JoueurHumain(String name, InteractionHumaine I){
        super(name);
        this.I = I;
    }

    @Override
    public int jouer(int nbRestantes) {
        I.setNbChoisi(0);
        synchronized (I.getSynchro()) {
            try {
                I.getSynchro().wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return I.obtenirNbChoisi();

    }
    @Override
    public void temporiser() throws InterruptedException { }
}
