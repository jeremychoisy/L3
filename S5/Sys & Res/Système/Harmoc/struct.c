#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "struct.h"

int main(int argc, char *argv[]){
  int nbj=0;
  int i;
  printf("Quel est le nombre de joueurs ?\n");
  scanf("%d",&nbj);
  Personne joueurs[nbj+1];
  for(i=0;i<nbj;i++)
  {
    printf("Nom du joueur %d\n",i+1);
    scanf("%s",joueurs[i].nom);
    printf("Prenom du joueur %d\n",i+1);
    scanf("%s",joueurs[i].prenom);
  }
  initialiserJoueur(&(joueurs[nbj]));
  for(i=0;i<=nbj;i++)
  {
    printf("Nom du joueur %d = %s\n",i+1,joueurs[i].nom);
    printf("Prenom du joueur %d = %s\n",i+1,joueurs[i].prenom);
  }
}

void initialiserJoueur(Personne *joueur){
  strcpy(joueur->nom,"Standard");
  strcpy(joueur->prenom ,"Standard");
}
