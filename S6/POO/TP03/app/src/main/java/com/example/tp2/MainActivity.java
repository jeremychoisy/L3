package com.example.tp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.tp2.controle.Controleur;
import com.example.tp2.controle.InteractionTactile;
import com.example.tp2.controle.InteractionTangible;
import com.example.tp2.controle.Settings;
import com.example.tp2.joueurHumain.JoueurHumain;
import com.example.tp2.moteur.FabriqueDeJoueur;
import com.example.tp2.moteur.JeuxDesAllumettes;
import com.example.tp2.moteur.Joueur;
import com.example.tp2.view.Allumettes;



public class MainActivity extends AppCompatActivity {
    private Settings settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Allumettes a = findViewById(R.id.allumettes);
        TextView t = findViewById(R.id.textview);
        ScrollView s = findViewById(R.id.scrollview);
        Button b = findViewById(R.id.button);
        settings = new Settings();

        //InteractionTactile I = new InteractionTactile(a);
        InteractionTangible I = new InteractionTangible(a,this);

        b.setOnClickListener(I);
       // a.setOnClickListener(I);

        Joueur j1 = FabriqueDeJoueur.obtenirUnJoueur(settings.getType1(), settings.getNomJoueur1());
        Joueur j2 = FabriqueDeJoueur.obtenirUnJoueur(settings.getType2(),settings.getNomJoueur2());

        JeuxDesAllumettes jeu = new JeuxDesAllumettes();

        jeu.setNbTotalAllumettes(settings.getNbAllumettes());
        jeu.ajouterJoueur(j1);
        jeu.ajouterJoueur(j2);

        if(settings.isPremierJoueur()) {
            jeu.setPremierJoueur(0);
        } else {
            jeu.setPremierJoueur(1);
        }

        Controleur c = new Controleur(jeu, a, t, s);
        c.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_reglages:
                startActivityForResult(settings.getSettings(this),1);
                return true;
        }
        return false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1) {
            settings.get(data);
        }
    }
}
