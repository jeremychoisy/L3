package com.example.tp1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp1.client.Client;
import com.example.tp1.listeners.Ecouteur;
import com.example.tp1.settings.Settings;
import com.example.tp1.R;

public class ChatActivity extends Activity implements Chat {
    private Settings s;
    private Ecouteur e;
    private Client c;

    @Override
    protected void onPause() {
        c.deconnexion();
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        s.saveIn(outState);
        outState.putString("chat",((TextView)findViewById(R.id.chat)).getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(((Switch)findViewById(R.id.connexion)).isChecked()){
            c.connexion();
        }
    }

    @Override
    public String obtenirTextTapé() {
        EditText eT =findViewById(R.id.message);
        return eT.getText().toString();

    }

    @Override
    public void ajouterMessage(final String msg) {
        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                TextView t = findViewById(R.id.chat);
                ScrollView s = findViewById(R.id.scroll);

                s.fullScroll(View.FOCUS_DOWN);
                t.append(msg + "\n");
            }
        });
    }

    public void ajouterMessage(final String msg, final int couleur){
        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                TextView t = findViewById(R.id.chat);
                ScrollView s = findViewById(R.id.scroll);

                int start = t.getText().length();
                t.append(msg + "\n");
                int end = t.getText().length();

                Spannable spannableText = (Spannable) t.getText();
                spannableText.setSpan(new ForegroundColorSpan(couleur), start, end, 0);


                s.fullScroll(View.FOCUS_DOWN);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.chat,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_reglages:
                startActivityForResult(s.obtenirRéglages(this),1);
                return true;
            case R.id.liste_connectes:
                c.demandeListeConnectes();
                return true;
        }
        return false;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1) {
            if (s.reçoit(data) == false) {
                Toast.makeText(this, "Veuillez entrer un prénom valide !\n\t\t\t\t Changement annulé.", Toast.LENGTH_SHORT).show();
            }
            c.creationSocket(s.getAdresse(),s.getPort());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s = new Settings();
        c = new Client(this,s);
        e = new Ecouteur(this, c);



        Button b = findViewById(R.id.envoyer);
        Switch sw = findViewById(R.id.connexion);
        TextView tv = findViewById(R.id.chat);

        if(savedInstanceState != null) {
            s.restoreFrom(savedInstanceState);
            tv.setText(savedInstanceState.getString("chat"));
        }


        b.setOnClickListener(e);
        sw.setOnCheckedChangeListener(e);


    }
}
