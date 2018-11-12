/*
 * extract MP3 tag frames
 * *
 * Author: Gilles MENEZ
 * Created: 2018
 */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <dirent.h>
#include <string.h>
#include <arpa/inet.h>

#include "util.h"
#include "id3_tagheader.h"
#include "id3_frame.h"


/*=====================================================*/
/* Pour comprendre comment parcourir un fichier mp3.

   On rend la position en fin de lecture : normalement
   on devrait etre en fin de tag.
*/

int mp3_get_frame_from_id (int fd ,char *id , char *content){
  frame_header fh;
  tag_header th;
  int nb_lu;

  /* On recupere les infos du tag, notamment la taille du tag */
  nb_lu = id3_read_tagheader(fd, &th);
  if (nb_lu != 10) return nb_lu;

  /* On recherche la frame correspondant à notre id */
  nb_lu = id3_read_frame_header(fd, &fh, th.tailletag);
  while(strcmp(fh.id,id)!=0 && nb_lu==10){
    lseek(fd, fh.tailleframe, SEEK_CUR);
    nb_lu = id3_read_frame_header(fd, &fh, th.tailletag);
  }
  /* Si nb_lu == 10 alors id3_read_frame_header s'est bien passé,
  donc la frame recherchée a été trouvée */
  if(nb_lu==10){
    content = malloc(sizeof(char)*50);
    if(!content){
      printf("ERROR");
    }
    /* on stock le contenu dans content */
    nb_lu = read_error(fd, content, fh.tailleframe, "Read Frame Content");
    /* Si le nombre lu ne correspond pas à la taille attendue du contenu */
    if(nb_lu!=fh.tailleframe){
      return -1;
    }
    /* sinon */
    printf("%s",content);
    strcat(content,"\0");
    return 0;
  }
  /* Si l'id n'a pas été trouvé */
  else
  {
    printf("Contenu introuvable.\n");
    return 0;
  }
}

off_t mp3_read(int fd){
  int nb_lu;
  int i;

  /* Tag Header analysis ---------------------*/
  tag_header th; /* on va recuperer le header du tag
		    car on en aura besoin pour parcourir les frames */
  nb_lu = id3_read_tagheader(fd, &th);
  if (nb_lu != 10) return nb_lu;

  /* Tag frames analysis ---------------------*/
  do {
    nb_lu = id3_read_frame(fd, th.tailletag);
  } while ( nb_lu > 0);

  /* Pour verifier ou on est a la fin de la lecture du tag ? -*/
  off_t fintag = lseek(fd, 0, SEEK_CUR);

#if DEBUG
  printf("\nFIN TAG : %d\n", fintag);
#endif

  return fintag;
}

/*=====================================================*/

int main(int argc, char *argv[]){
 FILE *f;
  char *filename;
  int nb_lu;
  int i;
  /* Verification de l'appel du programme ----*/
  if (argc != 2){ /* usage -- print usage message and exit */
    fprintf(stderr, "Usage: %s mp3file\n", argv[0]);
    exit(1);
  }
  else
    filename = argv[1];

  /* Ouverture du fichier --------------------*/
  if (! (f = fopen(filename, "r"))){
    perror(filename);
    exit(1);
  }

  int fd = fileno(f); /* Get descriptor from FILE * */
  char *content;
  /* 1) Parcours d'un fichier mp3 */

  mp3_get_frame_from_id(fd ,"TIT2",content);
  //mp3_read(fd);
  printf("%s",content);
  printf("\n");
  sync();

  return 0;
}

/*=====================================================*/
