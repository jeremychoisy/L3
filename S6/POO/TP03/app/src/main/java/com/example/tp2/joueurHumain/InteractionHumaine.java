package com.example.tp2.joueurHumain;

public interface InteractionHumaine {
    int obtenirNbChoisi(); // pour voir combien d’allumettes ont été choisies
    void setNbChoisi(int nbChoisi); // pour reset nbChoisis entre les tours
    Object getSynchro(); // pour fournir un objet de synchronisation, en vue d’un wait
}
