#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

#define CAPACITE 100
#define INTERVALLE_INVENTAIRE 1

int capacite_max_entrepot(){return CAPACITE;}

void *activite_entrepot(void *pdata){
  /* Affichage de l'inventaire de l'entrepot*/
  while(1){
    int *ps = (int*)(pdata); /*pointeur sur stock*/
    printf("Entrepot :\t");
    printf("Stock actuel : %d.\n",*(ps));
    sleep(INTERVALLE_INVENTAIRE);
  }
}


int creation_entrepot(pthread_t *t, int *pstock){
  /* Creation de l'entrepot */

  return pthread_create(t,
			NULL,
			activite_entrepot,
			pstock);
}
