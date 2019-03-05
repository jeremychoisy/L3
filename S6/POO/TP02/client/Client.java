package com.example.tp1.client;

import android.graphics.Color;


import com.example.tp1.settings.Settings;
import com.example.tp1.R;
import com.example.tp1.activities.ChatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Client{
    private ChatActivity chat;
    private Settings s;
    private Socket socket;

    public Client(ChatActivity c, Settings s){
        this.chat = c;
        this.s = s;
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

    public void envoyerMessage() {
        JSONObject j = new JSONObject();
        try {
            j.put("userName", s.getSurnom());
            j.put("message", chat.obtenirTextTapé());
        }catch(JSONException e){
            e.printStackTrace();
        }
        socket.emit("chatevent",j);
    }


    public void creationSocket(String adresse, String port){
        try {
            socket = IO.socket(adresse + ":" + port);

            socket.on("chatevent", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    JSONObject j = (JSONObject) args[0];
                    try {
                        chat.ajouterMessage("[" + j.get("userName") + "] " + j.get("message"));
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            });


            socket.on("connected list", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    JSONObject data = (JSONObject) args[0];
                    String buffer = "Liste des connectés : ";
                    try {
                        JSONArray connected = data.getJSONArray("connected");
                        buffer += connected.get(0);
                        for(int i = 1;i<connected.length();i++){
                            buffer += " ; " + connected.get(i);
                        }
                        chat.ajouterMessage(buffer, Color.RED);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });


        }catch(URISyntaxException e){
            creationSocket(s.getDefault_adresse(),s.getDefault_port());
            chat.ajouterMessage("Adresse du serveur incorrecte.",Color.RED);
            e.printStackTrace();
        }
    }

    public void demandeListeConnectes(){
        socket.emit("queryconnected", new JSONObject());
    }


    public void connexion(){
        creationSocket(s.getAdresse(),s.getPort());
        socket.connect();
        chat.ajouterMessage("Connecté.", Color.GREEN);
    }
    public void deconnexion(){
        if(socket.connected()) {
            socket.disconnect();
            socket.off("chatevent");
            socket.off("connected list");
            socket.close();
            chat.ajouterMessage("Déconnecté.",Color.GREEN);
        }
    }


}
