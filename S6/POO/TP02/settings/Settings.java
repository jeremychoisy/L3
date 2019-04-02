package com.example.tp1.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.tp1.activities.ReglagesActivity;

public class Settings {
    private String surnom;
    private String adresse;
    private String port;


    // default
    private String default_adresse;
    private String default_port;

    public Settings(){
        this.surnom = "Guest";

        this.default_adresse = "http://134.59.2.27";
        this.default_port = "10101";

        this.adresse = default_adresse;
        this.port = default_port;

    }

    public Intent obtenirRéglages(Context c) {
        return new Intent(c, ReglagesActivity.class);
    }

    public boolean reçoit(Intent data){
        String surnom = data.getStringExtra("surnom");
        String adresse = data.getStringExtra("adresse");
        String port = data.getStringExtra("port");

        if(!(adresse.isEmpty() || adresse == null) && !(port.isEmpty() || port == null)){
            this.adresse = adresse;
            this.port = port;
        }

        if(surnom.isEmpty() || surnom == null){
            return false;
        }
        else
        {
            this.surnom = surnom;
            return true;
        }

    }

    public void saveIn(Bundle b){
        b.putString("surnom",this.surnom);
        b.putString("adresse", this.adresse);
        b.putString("port", this.port);
    }

    public void restoreFrom(Bundle b){
        this.surnom = b.getString("surnom");
        this.adresse = b.getString("adresse");
        this.port = b.getString("port");
    }

    public String getSurnom() {
        return surnom;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDefault_adresse() {
        return default_adresse;
    }

    public void setDefault_adresse(String default_adresse) {
        this.default_adresse = default_adresse;
    }

    public String getDefault_port() {
        return default_port;
    }

    public void setDefault_port(String default_port) {
        this.default_port = default_port;
    }

}
