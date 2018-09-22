#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include "struct.h"

#define TEMPS_MOY_ENTRE_VENTE 5
#define TEMPS_MOY_ENTRE_APPRO 4

void *activite_concession(void *pdata){
  /* vente de voitures en continu toutes les 5 secondes */
  int stockLocal = 0;
  dataConcession *p = (dataConcession*)(pdata);
  /*On approvisionne le stock local de la concession à la création de celle-ci*/
  while(stockLocal<10){
    if(*(p->pstock)>0)
    {
      printf("Concession %d : stock local : %d\n",p->num,stockLocal);
      stockLocal += 1;
      *(p->pstock) = (*(p->pstock))-1;
    }
    sleep(TEMPS_MOY_ENTRE_APPRO);
  }
  /* On commence la vente et le réapprovisionnement */
  while(1){
    /* Vente */
    if(stockLocal > 0)
    {
    stockLocal -= 1;
    printf("Concession %d : vente d'une voiture. stock Local :%d\n",p->num,stockLocal);
    }
    /* Réapprovisionnement après une vente */
    if(*(p->pstock)>0 && stockLocal<10)
    {
      stockLocal += 1;
      *(p->pstock) = (*(p->pstock))-1;
    }
    sleep(TEMPS_MOY_ENTRE_VENTE);
  }
}



int creation_concession(pthread_t *t, dataConcession *pdata){
  /* Creation de l'usine */
  return pthread_create(t,
			NULL,
			activite_concession,
			pdata);
}
