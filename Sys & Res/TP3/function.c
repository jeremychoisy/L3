#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include "function.h"

/*======================================================*/
char * sh_read_line ( void ) {
  char * line = NULL ;
  ssize_t bufsize = 0 ; // donc getline realise l’allocation
  getline(&line , &bufsize , stdin ) ;
  return line ;
}

/*======================================================*/
# define LSH_TOK_BUFSIZE 64
# define LSH_TOK_DELIM ", \t\n"

char ** sh_split_line ( char * line ) {
  int bufsize = LSH_TOK_BUFSIZE , position = 0 ;
  char ** tokens = malloc ( bufsize * sizeof ( char * ) ) ;
  char * token ;
  if ( ! tokens ) {
      fprintf ( stderr , " lsh : allocation   error \n" ) ;
      exit ( EXIT_FAILURE ) ;
    }
  token = strtok ( line , LSH_TOK_DELIM ) ;
  while ( token != NULL ) {
    tokens [ position ] = token ;
    position++;
    if ( position >= bufsize ) {
      bufsize += LSH_TOK_BUFSIZE ;
      tokens = realloc ( tokens , bufsize * sizeof ( char * ) ) ;
      if ( ! tokens ) {
        fprintf ( stderr , " lsh :  allocation   error \n" ) ;
        exit ( EXIT_FAILURE ) ;
      }
    }
    token = strtok ( NULL , LSH_TOK_DELIM ) ;
  }
    tokens [ position ] = NULL ;
    return tokens ;
}

/*======================================================*/
#define BUFFSIZE 100

int sh_execute ( char ** args, char ** forbiddens ) {
  int i=0, j=0, execR=0;
  // On compare la liste de tokens de forbiddens avec celle d'args.
  while(args[i]!=NULL)
  {
    j=0;
    while(forbiddens[j]!=NULL)
    {
      // Si on trouve une correspondance, il s'agit d'une commande ou d'un arg
      // censuré, on interrompt donc la fonction sh_execute.
      if(strcasestr(args[i],forbiddens[j])!=NULL)
      {
        printf("Travaille au lieu de jouer !\n");
        return EXIT_SUCCESS;
      }
      j += 1;
    }
    i += 1;
  }
  // Sinon on execute la commande
  execR=execvp(args[0],args);
  // execvp n'aura un retour que si et seulement son execution s'est mal
  // déroulé.
  if(execR!=0)
  {
    printf("l3miageshell: %s : commande introuvable.\n",args[0]);
  }
}

/*======================================================*/
// Handler du signal SIGCHLD */
void handlerSIGCHLD(int sig){
  int exit_cond ;
  pid_t pid;
  pid =wait(&exit_cond);

  if (!WIFEXITED(exit_cond))
    printf (" Le fils %d s’est mal termine : %d\n" , pid , WTERMSIG ( exit_cond ));
}

/*======================================================*/
void sh_loop ( void ) {
  char *prompt = "l3miage  shell  >  " ;
  char *line ;
  char **args ;

// variables forbidden
  char * f = malloc(sizeof(char)*BUFFSIZE);
  char **forbiddens;

 // variables newf/rmf
  int i, j, k;
  int b = 0;

 // variables fork
  int status = 0;
  pid_t pid;

// struc sigaction pour la gestion de SIGCHLD
  struct sigaction action;

// Si la variable d'env n'est pas nulle, on l'a récupère et l'on place les différents
//tokens dans forbiddens. (malloc dans la fonction sh_split_line)
  if(getenv("FORBIDDEN")!=NULL)
  {
    strcpy(f,getenv("FORBIDDEN"));
    printf("Récupération des variables FORBIDDEN depuis l'env : %s\n",f);
    forbiddens=sh_split_line ( f );
  }
// Sinon on alloue de la mémoire à forbiddens et on place le NULL à la fin.
  else
  {
    forbiddens=malloc(BUFFSIZE*sizeof(char*));
    if(forbiddens==NULL)
    {
      fprintf ( stderr , " lsh : allocation   error \n" ) ;
      exit ( EXIT_FAILURE ) ;
    }
    forbiddens[0]=NULL;
    printf("Initialisation de FORBIDDEN : utilisez newf/rmf pour modifier la liste.\n");
  }
  do
  {
    printf ( "%s" , prompt ) ;
    fflush ( stdout ) ;

    line = sh_read_line ( ) ;
    args = sh_split_line ( line ) ;

    if(args[0]!=NULL)
    {
      // Cas du exit
      if(strcmp(args[0],"exit")==0)
      {
        free(f);
        free(forbiddens);
        free(args);
        exit(0);
      }

      if(strcasecmp(args[0],"newf")==0)
      {
        // On vérifie que l'argument n'est pas déjà présent dans la liste des forbiddens.
        // On réinitialise les variables à chaque nouveau passage.
        b=0;
        i=1;
        while(args[i]!=NULL)
        {
          j=0;
          while(forbiddens[j]!=NULL)
          {
            if(strcasecmp(args[i],forbiddens[j])==0)
            {
              printf("Contrainte déjà présente dans la variable FORBIDDEN !\n");
              b=1;
            }
            j += 1;
          }
          i += 1;
        }
        if(!b)
        {
          // On ajoute les nouveaux tokens à forbiddens, on récupère l'indice de
          // fin du tableau grâce au j du while précèdent.
          i=1;
          while(args[i]!=NULL)
          {
              forbiddens[j]=args[i];
              i++;
              j++;
          }
          forbiddens[j]=NULL;
        }
      }
      // cas du rmf
      else if(strcasecmp(args[0],"rmf")==0)
      {
        // On cherche le token ciblé dans forbiddens.
        i=1;
        while(args[i]!=NULL)
        {
          j=0;
          while(forbiddens[j]!=NULL)
          {
            // Une fois trouvé, on l'écrase en décalant tous les valeurs suivantes
            // d'une case vers l'arrière et l'on marque la nouvelle fin du tableau
            // avec NULL.
            if(strcasecmp(args[i],forbiddens[j])==0)
            {
              k=j;
              while(forbiddens[k+1]!=NULL)
              {
                forbiddens[k]=forbiddens[k+1];
                k++;
              }
              forbiddens[k]=NULL;
            }
            j += 1;
          }
          i += 1;
        }

      }
      else
      {
        pid=fork();
        if(pid == -1)
        {
          printf("Erreur fork.\n");
        }
        else if(pid==0)
        {
          status = sh_execute ( args, forbiddens ) ;
          exit(0);
        }
        else
        {
          // On met en place la gestion de SIGCHLD.
          memset(&action,0,sizeof(action));
          action.sa_handler = handlerSIGCHLD;
          action.sa_flags=0;
          sigemptyset(&action.sa_mask);
          sigaction (SIGCHLD,&action,NULL);
          sleep(5);
        }
      }
    }
/* s h_ f r e e ( l i n e ) ; */
/* s h_ f r e e ( a r g s ) ; */
  } while ( status == 0 ) ;
}
