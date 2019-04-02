package com.example.tp1.listeners;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.tp1.settings.Settings;
import com.example.tp1.R;
import com.example.tp1.activities.ChatActivity;

import com.example.tp1.client.Client;

public class Ecouteur implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private ChatActivity chat;
    private Client client;

    public Ecouteur(ChatActivity c, Client client){
        this.chat = c;
        this.client = client;
        enableChat(false);
    }

    public void enableChat(boolean b){
        if(b == true){
            chat.findViewById(R.id.message).setEnabled(true);
            chat.findViewById(R.id.envoyer).setEnabled(true);
        }
        else
        {
            chat.findViewById(R.id.message).setEnabled(false);
            chat.findViewById(R.id.envoyer).setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
       client.envoyerMessage();
       ((EditText)chat.findViewById(R.id.message)).setText("");
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            client.connexion();
            enableChat(true);
        }
        else
        {
            enableChat(false);
            client.deconnexion();
        }
    }
}
