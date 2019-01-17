#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]){
  FILE *fichier = NULL;
  char chaine[100] ="";
  fichier = fopen("test.txt","r");
  printf("%d\n",fichier);
  if(fichier == NULL){
    exit(0);
  }
    fgets(chaine,100,fichier);
    printf("%s\n",chaine);
    fclose(fichier);
    return 0;
}
