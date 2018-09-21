#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define NB_CONCESSION 3
int creation_usine(pthread_t *t, int *stock);
int creation_concession(pthread_t *t, int **tab);
int creation_entrepot(pthread_t *t, int *stock);

int main(int argc, char*argv[]){
  int r = 1; /*report value*/
  int i;
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
  for(i=0;i<NB_CONCESSION;i++)
  {
    /* on stock la valeur de i dans le tas */
    int **tab=NULL;
    tab = malloc(sizeof(int*)*2);
    tab[0]=&stock;
    tab[1]=&i;
    r = creation_concession(&(concession[i]),tab);
    /* vérification de la réussite de la création du thread*/
    if (r != 0)
      fprintf(stderr,"Concession echec !");
  }

    /* attente de l'exécution des autres threads*/
    pthread_join(usine,NULL);
    pthread_join(entrepot,NULL);
    for(i=0;i<NB_CONCESSION;i++){
      pthread_join(concession[i],NULL);
    }
    return 0;
}
