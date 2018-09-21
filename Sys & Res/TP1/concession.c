#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

#define CAPACITE_CONCESSION 10
#define TEMPS_MOY_ENTRE_VENTE 5

void *activite_concession(void *pdata){
  /* vente de voitures en continu toutes les 5 secondes */
  int **tab= NULL;
  tab = (int**)(pdata);
  while(1){
    if(*(tab[0])>=1)
    {
      printf("Concession %d : vente d'une voiture.\nStock : %d\n",*(tab[1]),*(tab[0]));
      *(tab[0])=(*(tab[0]))-1;
      sleep(TEMPS_MOY_ENTRE_VENTE);
    }
  }
}


int creation_concession(pthread_t *t, int **tab){
  /* Creation de l'usine */
  return pthread_create(t,
			NULL,
			activite_concession,
			tab);
}
