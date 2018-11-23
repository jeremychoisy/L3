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
#include <sys/stat.h>
#include <dirent.h>
#include <string.h>
#include <arpa/inet.h>

#include "util.h"
#include "id3_tagheader.h"
#include "id3_frame.h"
#include "dirent.h"

int mp3_get_frame_from_id (int fd ,char *id , char *content);
char ** ls_mp3files_inarray (char* extension, char* pathname);
char * mp3_get_album_title(int fd);
/*=====================================================*/
/* Pour comprendre comment parcourir un fichier mp3.

   On rend la position en fin de lecture : normalement
   on devrait etre en fin de tag.
*/
char ** ls_mp3files_inarray (char* extension, char * pathname){
  char ** tab_result = NULL;
  DIR  *dd ;

  struct dirent *entree;

  int compt = 0;
  int i =0;

  char * point = NULL;;
  char * buff;

  dd = opendir(pathname);
  if ( dd == NULL ) {
    fprintf (stderr ," Ouverture répertoire impossible .\n",pathname) ;
    return NULL;
  }
  while ( (entree = readdir(dd)) != NULL ) {
    if((point = strrchr(entree->d_name,'.')) != NULL ) {
     if(strcmp(point,".mp3") == 0) {
              compt += 1;
      }
    }
  }

  if(compt != 0){
    rewinddir(dd);
    tab_result = malloc(sizeof(char*)*(compt+1));
    while ( (entree = readdir(dd)) != NULL ) {;
      if((point = strrchr(entree->d_name,'.')) != NULL ) {
        if(strcmp(point,".mp3") == 0) {
          buff=malloc(sizeof(char)*strlen(entree->d_name));
          strcpy(buff,entree->d_name);
          tab_result[i] = buff;
          i++;
        }
      }
    }
    tab_result[i]=NULL;
  }
  closedir(dd);
  return tab_result;
}

/******************************************************************************/

char * mp3_get_album_title(int fd){
  char * content = malloc(sizeof(char)*100);
  int cr;
  cr = mp3_get_frame_from_id(fd,"TALB", content);
  if(cr==-1){
    perror("Nom de l'album introuvable");
    return NULL;
  }
  /*  On remet la la tête de lecture au début du fichier */
  lseek(fd,0,SEEK_SET);
  return content;
}

/******************************************************************************/

int mp3_get_frame_from_id (int fd ,char *id , char *content){
  frame_header fh;
  tag_header th;
  int nb_lu;
  char *frame_id;

  /* On recupere les infos du tag, notamment la taille du tag */
  nb_lu = id3_read_tagheader(fd, &th);
  if (nb_lu != 10) return nb_lu;

  /* On recherche la frame correspondant à notre id */
  nb_lu = id3_read_frame_header(fd, &fh, th.tailletag);

  frame_id=malloc(sizeof(char)*5);
  /* On transforme l'id en string */
  strcpy(frame_id,fh.id);
  strcat(frame_id,"\0");

  while(strcmp(frame_id,id)!=0 && nb_lu==10){
    lseek(fd, fh.tailleframe, SEEK_CUR);
    nb_lu = id3_read_frame_header(fd, &fh, th.tailletag);

    strcpy(frame_id,fh.id);
    strcat(frame_id,"\0");
  }
  /* Si nb_lu == 10 alors id3_read_frame_header s'est bien passé,
  donc la frame recherchée a été trouvée */
  if(nb_lu==10){
    if(!content){
      perror("Frame introuvable");
    }
    lseek(fd, 1, SEEK_CUR);
    /* on stock le contenu dans content */
    nb_lu = read_error(fd, content, fh.tailleframe, "Read Frame Content");
    /* Si le nombre lu ne correspond pas à la taille attendue du contenu */
    if(nb_lu!=fh.tailleframe){
      return -1;
    }
    /* sinon */
    strcat(content,"\0");
    lseek(fd,0,SEEK_SET);
    return 0;
  }
  /* Si l'id n'a pas été trouvé */
  else
  {
    printf("Contenu introuvable.\n");
    /*  On remet la la tête de lecture au début du fichier */
    lseek(fd,0,SEEK_SET);
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
  char *pathname="";
  int nb_lu;
  int i = 0;
  char **tab;
  char *filepath;
  char *new_rep;
  char *album, *title, *track;
  char *old_file, *new_file;
  /* Verification de l'appel du programme ----*/
  if (argc != 2){ /* usage -- print usage message and exit */
    fprintf(stderr, "%s : missing argument, pathname expected.\n", argv[0]);
    exit(1);
  }
  else
    pathname = argv[1];

  /* Récupération des fichiers mp3 dans le répertoire cible */
  tab = ls_mp3files_inarray (".mp3",pathname);
  if (tab == NULL){
    printf( "%s : Couldn't find any mp3 files in the repository.\n", argv[0]);
    exit(0);
  }
  filepath = malloc(sizeof(char)*50);
  /* construction du chemin du premier ficher */
  strcpy(filepath,pathname);
  strcat(filepath,"/");
  strcat(filepath,tab[0]);

  /* Ouverture du premier fichier --------------------*/
  if (! (f = fopen(filepath, "r"))){
    perror(filepath);
    exit(1);
  }

  int fd = fileno(f); /* Get descriptor from FILE * */

  /* récupération du nom de l'album dans le 1er fichier */
  album = mp3_get_album_title(fd);

  new_rep = malloc(sizeof(char)*(strlen(album)+strlen(pathname)+1));

  /* construction du chemin du nouveau répertoire */
  strcpy(new_rep,pathname);
  strcat(new_rep,"/");
  strcat(new_rep,album);

  /* création du nouveau répertoire nommé d'aprés le nom de l'album */
  mkdir(new_rep,ACCESSPERMS);
//  mp3_get_frame_from_id(fd ,"TIT2",content);
  //mp3_read(fd);
  fclose(f);

  old_file = malloc(sizeof(char)*50);
  new_file = malloc(sizeof(char)*50);
  title = malloc(sizeof(char)*50);
  track = malloc(sizeof(char)*50);

  while(tab[i]!=NULL){
    /* construction du chemin de chaque fichier */
    strcpy(old_file,pathname);
    strcat(old_file,"/");
    strcat(old_file,tab[i]);

    /* Ouverture du fichier --------------------*/
    if (! (f = fopen(old_file, "r"))){
      perror(old_file);
      exit(1);
    }

    fd = fileno(f); /* Get descriptor from FILE * */

    mp3_get_frame_from_id(fd,"TIT2",title);
    mp3_get_frame_from_id(fd,"TRCK",track);
    /* construction du nouveau chemin et nom du fichier */
    strcpy(new_file,new_rep);
    strcat(new_file,"/");
    strcat(new_file,title);
    strcat(new_file,"_");
    strcat(new_file,track);
    strcat(new_file,".mp3");

    if(rename(old_file,new_file)==-1){
      perror("rename");
      exit(1);
    }

    printf("%s => %s\n",old_file,new_file);

    fclose(f);
    i++;

  }

  printf("Success !\n");

  sync();

  return 0;
}

/*=====================================================*/
