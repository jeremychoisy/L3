package com.example.tp2.controle;

import android.view.View;

import com.example.tp2.R;
import com.example.tp2.joueurHumain.InteractionHumaine;
import com.example.tp2.view.Allumettes;

public class InteractionTactile implements InteractionHumaine, View.OnClickListener {
    private Allumettes a;
    private int nbChoisi;
    public final Object Synchro = new Object();

    public InteractionTactile(Allumettes a){
        this.a = a;
        nbChoisi = 0;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.allumettes:
                nbChoisi = (nbChoisi+1) % 4;
                a.setSelectedAllumettes(nbChoisi);
                break;
            case R.id.button:
                synchronized (Synchro) {
                    Synchro.notify();
                }
                break;
        }
    }

    @Override
    public void setNbChoisi(int nbChoisi){
        this.nbChoisi = nbChoisi;
    }

    @Override
    public int obtenirNbChoisi() {
        return nbChoisi;
    }

    @Override
    public Object getSynchro() {
        return Synchro;
    }
}
