#include <stdio.h>
#include <stdlib.h>
#include "image.h"
#include <string.h>

image *lecture_image(FILE *f){
  image *I=malloc(sizeof(image));
  int k=0;
  char * str = malloc(sizeof(char)*5000);
  int c;

  //nm
  if(fscanf(f,"%s",I->nm)!=1){
    printf("Erreur fscanf : nm\n ");}
  c = fgetc(f);

  //comments
  while(fscanf(f,"# %[^\n]",str)==1){
  strcat(I->comments,"# ");
  strcat(I->comments,str);
  c = fgetc(f);
  }

  //nl,nc
  fscanf(f,"%d %d",&(I->nl),&(I->nc));

  //ng
  fscanf(f,"%d",&(I->ng));

  //pixels
  I->pixels = malloc(sizeof(unsigned char)*(I->nl*I->nc));
  while(fscanf(f,"%u",&(I->pixels[k]))==1){
    k++;
  }

  free(str);
  return I;
}

/*======================================================*/
void inverse_image(image *I){
  for(int k=0;k<(I->nl*I->nc);k++)  {
    I->pixels[k]= I->ng - I->pixels[k];
  }
}

/*======================================================*/
void ecriture_image(FILE *f, image *I){
  char *buff = malloc(sizeof(char)*100);
  //nm
  fprintf(f,"%s\n",I->nm);

  //comments
  buff=strtok(I->comments,"#");
  while(buff!=NULL){
    fprintf(f,"#%s\n",buff);
    buff=strtok(NULL,"#");
  }

  // nl,nc
  fprintf(f,"%d %d\n",I->nl,I->nc);

  //ng
  fprintf(f,"%d\n",I->ng);

  //pixels
  for(int k=0;k<(I->nl*I->nc);k++)  {
    fprintf(f,"%u\n",I->pixels[k]);
  }
}
