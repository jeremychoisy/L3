package com.example.tp2.controle;

import android.graphics.Color;
import android.os.AsyncTask;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.tp2.moteur.JeuxDesAllumettes;
import com.example.tp2.view.Allumettes;

public class Controleur extends AsyncTask<Object,String,Object> {
    private JeuxDesAllumettes jeu;
    private Allumettes viewAllumettes;
    private TextView t;
    private ScrollView s;

    public Controleur(JeuxDesAllumettes jeu, Allumettes viewAllumettes, TextView t, ScrollView s){
        this.jeu = jeu;
        this.viewAllumettes = viewAllumettes;
        this.t = t;
        this.s = s;
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        int nbAllumettesPrises;
        if(jeu.partieComplete()){
            jeu.démarrer(true);
            publishProgress("La partie commence !");
            while(jeu.obtenirGagnant() == null) {
                nbAllumettesPrises = jeu.jouerUnTour();
                publishProgress(jeu.getJoueurCourant().toString() + " a sélectionné " + nbAllumettesPrises + " allumettes.");
                viewAllumettes.setSelectedAllumettes(nbAllumettesPrises);
                try {
                    jeu.getJoueurCourant().temporiser();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                viewAllumettes.setSelectedAllumettes(0);
                publishProgress(jeu.getJoueurCourant().toString() + " a retiré " + nbAllumettesPrises + " allumettes.");
                viewAllumettes.setCurrentAllumettes(jeu.obtenirNbAllumettesRestantes() - nbAllumettesPrises);
                jeu.setNbAllumettesRestantes(jeu.obtenirNbAllumettesRestantes() - nbAllumettesPrises);
                publishProgress("Il reste " + jeu.obtenirNbAllumettesRestantes() + " allumettes.");
                jeu.aQuiDeJouer();
            }
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(String...values) {
        t.append(values[0] + "\n");
        s.fullScroll(View.FOCUS_DOWN);
    }

    @Override
    protected void onPostExecute(Object o) {

        int start = t.getText().length();
        t.append("LE GAGNANT EST : " + jeu.obtenirGagnant().toString() + "!\n");
        int end = t.getText().length();

        Spannable spannableText = (Spannable) t.getText();
        spannableText.setSpan(new ForegroundColorSpan(Color.GREEN), start, end, 0);

        s.fullScroll(View.FOCUS_DOWN);

    }

}

