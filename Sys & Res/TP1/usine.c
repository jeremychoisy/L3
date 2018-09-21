#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

#define TEMPS_PAR_PRODUCTION 1
int capacite_max_entrepot();

void *activite_usine(void *pdata){
  /* production de voitures en continu toutes les 1 secondes */
  while(1){
    int *ps = (int *)(pdata); /*pointeur sur stock */
    printf("Usine : \t");
    if(*ps<capacite_max_entrepot()){
      (*ps)++;
      printf("Production d'une voiture.\n");
    }else{
      printf("Pas de production d'une voiture.\n");
    }
    sleep(TEMPS_PAR_PRODUCTION);
  }
}


int creation_usine(pthread_t *t, int *pstock){
  /* Creation de l'usine */

  return pthread_create(t,
			NULL,
			activite_usine,
			pstock);
}
