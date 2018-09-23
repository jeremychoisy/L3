#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include "struct.h"
/* déclaration static du mutex */
static pthread_mutex_t pmutex = PTHREAD_MUTEX_INITIALIZER;


#define NB_CONCESSION 3
int creation_usine(pthread_t *t, dataUE *pdata);
int creation_concession(pthread_t *t, dataConcession *pdata);
int creation_entrepot(pthread_t *t, dataUE *pdata);

int main(int argc, char*argv[]){
  int r = 1; /*report value*/
  int stock = 0; /* déclaration stock de l'entrepot */

  /*déclaration threads et structures*/
  /* modèle usine */
  pthread_t usine;
  dataUE *pdataU = malloc(sizeof(dataUE));
  pdataU->pmutex = pmutex;
  pdataU->pstock = &stock;

  /* modèle entrepot */
  pthread_t entrepot;
  dataUE *pdataE = malloc(sizeof(dataUE));
  pdataE->pmutex = pmutex;
  pdataE->pstock = &stock;
  /* modèle concession */
  pthread_t concession[NB_CONCESSION];
  dataConcession *pdataC = NULL;

  /* création thread usine */
  r = creation_usine(&usine,pdataU);
  /* vérification de la réussite de la création du thread*/
  printf("creation_usine");
  if (r != 0)
    fprintf(stderr,"Usine echec !");
  /* création thread entrepot */
  r = creation_entrepot(&entrepot, pdataE);
    printf("creation_entrepot");
  /* vérification de la réussite de la création du thread*/
  if (r != 0)
    fprintf(stderr,"Entrepot echec !");
  /* création threads concession */
  for(int i=0;i<NB_CONCESSION;i++)
  {
    /* Déclaration d'un pointeur sur une structure dataConcession stockée
    dans le tas (nouvelle structure stocké dans un nouvel emplacement mémoire
    à chaque itération)*/
    dataConcession *pdataC = malloc(sizeof(dataConcession));
    pdataC->num = i;
    pdataC->pstock = &stock;
    /* On ouvre les concessions au fur et à mesure de façon à ce
    qu'il y ait au moins une voiture disponible par concession à leur
    ouverture */
    if(stock>i)
    {
      r = creation_concession(&(concession[i]),pdataC);
      printf("creation_entrepot");
      /* vérification de la réussite de la création du thread*/
      if (r != 0)
        fprintf(stderr,"Concession echec !");
    }
    else
    {
      i-=1;
    }
  }

    /* attente de l'exécution des autres threads*/
    pthread_join(usine,NULL);
    pthread_join(entrepot,NULL);
    for(int i=0;i<NB_CONCESSION;i++){
      pthread_join(concession[i],NULL);
    }
    free(pdataC);
    free(pdataE);
    free(pdataU);
    return 0;
}
