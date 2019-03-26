package com.example.tp2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tp2.view.ChoixDuType;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ChoixDuType choixDuType1 = ChoixDuType.newInstance("J1");
        ChoixDuType choixDuType2 = ChoixDuType.newInstance("J2");

        fragmentTransaction.add(R.id.fragment_container,choixDuType1);
        fragmentTransaction.add(R.id.fragment_container,choixDuType2);
        fragmentTransaction.commit();


    }
}
