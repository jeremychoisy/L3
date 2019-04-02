package com.example.tp1.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tp1.R;

public class ReglagesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglages);


        Button b = findViewById(R.id.retour);

        b.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        EditText e_surnom = findViewById(R.id.edittext_pref);
        EditText e_adresse = findViewById(R.id.edittext_address_pref);
        EditText e_port = findViewById(R.id.edittext_port_pref);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("surnom", e_surnom.getText().toString());
        returnIntent.putExtra("adresse", e_adresse.getText().toString());
        returnIntent.putExtra("port", e_port.getText().toString());
        setResult(1,returnIntent);
        super.onBackPressed();
    }
}
