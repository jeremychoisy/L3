package com.example.tp2.controle;

import android.app.Activity;
import android.content.Context;
import android.content.SyncRequest;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;

import com.example.tp2.joueurHumain.InteractionHumaine;
import com.example.tp2.view.Allumettes;

import static android.content.ContentValues.TAG;

public class InteractionTangible implements InteractionHumaine, View.OnClickListener, SensorEventListener {
    private Allumettes a;
    private Context c;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private int nbChoisi;
    private final Object Synchro = new Object();

    public long time;

    public InteractionTangible(Allumettes a, Context c){
        this.a = a;
        this.c = c;
        nbChoisi = 0;
        sensorManager = (SensorManager)c.getSystemService((Context.SENSOR_SERVICE));
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void start(){
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop(){
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            if(time < System.currentTimeMillis()) {
                if (event.values[0] < -3f) {
                    if(nbChoisi % 4 < 3) {
                        nbChoisi = (nbChoisi + 1) % 4;
                        a.setSelectedAllumettes(nbChoisi);
                        time = System.currentTimeMillis() + 500;
                    }
                } else if (event.values[0] > 3f) {
                    nbChoisi = (nbChoisi - 1) % 4;
                    a.setSelectedAllumettes(nbChoisi);
                    time = System.currentTimeMillis() + 500;
                }
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClick(View v) {
        synchronized (Synchro) {
            Synchro.notify();
        }
    }

    @Override
    public int obtenirNbChoisi() {
        stop();
        return nbChoisi;
    }

    @Override
    public void setNbChoisi(int nbChoisi) {
        this.nbChoisi = nbChoisi;
    }

    @Override
    public Object getSynchro() {
        start();
        return Synchro;
    }
}
