#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include "struct.h"


#define NB_CONCESSION 3
int creation_usine(pthread_t *t, int *pstock);
int creation_concession(pthread_t *t, dataConcession *pdata);
int creation_entrepot(pthread_t *t, int *pstock);

int main(int argc, char*argv[]){
  int r = 1; /*report value*/
  /*déclaration threads*/
  /* modèle usine */
  pthread_t usine;
  /* modèle entrepot */
  pthread_t entrepot;
  int stock = 0;
  /* modèle concession */
  pthread_t concession[NB_CONCESSION];

  /* création thread usine */
  r = creation_usine(&usine,&stock);
  /* vérification de la réussite de la création du thread*/
  if (r != 0)
    fprintf(stderr,"Usine echec !");
  /* création thread entrepot */
  r = creation_entrepot(&entrepot, &stock);
  /* vérification de la réussite de la création du thread*/
  if (r != 0)
    fprintf(stderr,"Entrepot echec !");

  /* création threads concession */
  for(int i=0;i<NB_CONCESSION;i++)
  {
    /* Déclaration d'un pointeur sur une structure dataConcession stockée
    dans le tas (nouvelle structure stocké dans un nouvel emplacement mémoire
    à chaque itération)*/
    dataConcession *pdata = malloc(sizeof(dataConcession));
    pdata->num = i;
    pdata->pstock = &stock;
    /* On ouvre les concessions au fur et à mesure de façon à ce
    qu'il y ait au moins une voiture disponible par concession à leur
    ouverture */
    if(stock>i)
    {
      r = creation_concession(&(concession[i]),pdata);
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

    return 0;
}
