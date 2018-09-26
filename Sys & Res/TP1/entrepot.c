#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include "struct.h"

#define CAPACITE 100
#define INTERVALLE_INVENTAIRE 1

int capacite_max_entrepot(){return CAPACITE;}

void *activite_entrepot(void *pdata){
  /* Affichage de l'inventaire de l'entrepot*/
  dataUE *pdataE = (dataUE *)(pdata);
  while(1){
    pthread_mutex_lock(pdataE->pmutex);
    printf("Entrepot :\t");
    printf("Stock actuel : %d.\n",*(pdataE->pstock));
    pthread_mutex_unlock(pdataE->pmutex);
    sleep(INTERVALLE_INVENTAIRE);
  }
}


int creation_entrepot(pthread_t *t, dataUE *pdata){
  /* Creation de l'entrepot */

  return pthread_create(t,
			NULL,
			activite_entrepot,
			pdata);
}
