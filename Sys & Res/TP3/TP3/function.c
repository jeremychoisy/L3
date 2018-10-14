
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>

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
#define BUFFSIZE 64

int sh_execute ( char ** args, char ** forbiddens ) {
  int i=1,j=0;
  char * strbuff = malloc(sizeof(char)*BUFFSIZE);
  char * strEnv = malloc(sizeof(char)*BUFFSIZE);
// cas du newf
    if(strcmp(args[0],"newf")==0)
    {
      // On vérifie que l'argument n'est pas déjà présent dans la liste des forbiddens.
      while(args[i]!=NULL)
      {
        while(forbiddens[j]!=NULL)
        {
          if(strcmp(args[i],forbiddens[j])==0)
          {
            printf("Contrainte déjà présente dans la variable FORBIDDEN !\n");
            exit(0);
          }
          j += 1;
        }
        i += 1;
      }
      i=1,j=0;
      /* On construit la liste des forbiddens sous la forme d'une chaîne de caractères
        adéquate. */
      while(forbiddens[j]!=NULL)
      {
        strbuff=strcat(strbuff,forbiddens[j]);
        strbuff=strcat(strbuff,",");
        j += 1;
      }
      // On concatène celle-ci avec les nouveaux verrous passés en arguments.
      while(args[i]!=NULL)
      {
        strbuff=strcat(strbuff,args[i]);
        strbuff=strcat(strbuff,",");
        i += 1;
      }
      // On retire le dernier caractère ",".
      strEnv = strncat(strEnv, strbuff, strlen(strbuff)-1);
      setenv("FORBIDDEN",strEnv,1);
    }
// cas du rmf
    else if(strcmp(args[0],"rmf")==0)
    {
// On construit une chaîne de caractère des forbiddens qu'on place dans strbuff.
        while(forbiddens[j]!=NULL)
        {
          strbuff = strcat(strbuff,forbiddens[j]);
          strbuff = strcat(strbuff,",");
          j += 1;
        }
// On compare chaque argument à tous les éléments forbiddens.
        while(args[i]!=NULL)
        {
          /* Si on trouve l'argument dans la chaîne de caractère construit
          au préalable, on crée une nouvelle chaîne avec les éléments précédents
          et suivants celui-ci. */
          if(strstr(strbuff,args[i])!=NULL)
          {
            strEnv = strncat(strEnv, strbuff, strstr(strbuff,args[i])-strbuff);
            strEnv = strncat(strEnv,strstr(strbuff,args[i])+strlen(args[i]) + 1, strlen(strbuff));
            /* On met la valeur de strEnv dans strbuff et on réinitialise strEnv
               pour pouvoir travailler de manière récursive dans la cas de plusieurs
               arguments. */
            strcpy(strbuff,strEnv);
            strcpy(strEnv,"");
          }
          i += 1;
        }
        // On enlève la dernière virgule avant de set FORBIDDEN
        strEnv = strncat(strEnv, strbuff, strlen(strbuff)-1);
        setenv("FORBIDDEN",strEnv,1);
    }
// cas général
    else
    {
      i=0,j=0;
      while(args[i]!=NULL)
      {
        while(forbiddens[j]!=NULL)
        {
          if(strstr(args[i],forbiddens[j])!=NULL)
          {
            printf("Travaille au lieu de jouer !\n");
            exit(0);
          }
          j += 1;
        }
        i += 1;
        j= 0;
      }
      char *str=malloc(sizeof(char)*(strlen("/bin/")));
      strcpy(str,"/bin/");
      execv(strcat(str,args[0]),args);
      return 0;
  }
}
void gestionFils(int sig){
  int exit_cond ;
  pid_t pid;
  pid = wait(&exit_cond);
  if ( !WIFEXITED ( exit_cond ))
    printf (" Le fils %d s ’ est mal termine : %d\n" , pid ,
    WTERMSIG ( exit_cond ));
}

void gestionCtrlC(int sig){
  printf("warning\n");
}
/*======================================================*/
void sh_loop ( void ) {
  char *prompt = "l3miage  shell  >  " ;
  char *line ;
  char **args ;
  char * f = malloc(sizeof(char)*BUFFSIZE);
  char **forbiddens;
  int status;
  pid_t pid;
  struct sigaction actionfils,actionctrlc;



  if(getenv("FORBIDDEN")!=NULL)
  {
    strcpy(f,getenv("FORBIDDEN"));
    printf("Get FORBIDDEN variable(s) from env : %s\n",f);
  }
  else
  {
    setenv("FORBIDDEN","",0);
    printf("Initialisation de la variable d'environnement FORBIDDEN.\n ");
  }

  do
  {
    memset(&actionctrlc,0,sizeof(actionctrlc));
    actionctrlc.sa_handler=gestionCtrlC;
    actionctrlc.sa_flags=0;
    sigemptyset(&actionctrlc.sa_mask);
    sigaction(SIGINT,&actionctrlc,NULL);

    strcpy(f,getenv("FORBIDDEN"));
    forbiddens=sh_split_line ( f );

    printf ( "%s  " , prompt ) ;
    fflush ( stdout ) ;

    line = sh_read_line ( ) ;
    args = sh_split_line ( line ) ;
    // Cas du exit

    if(strcmp(args[0],"exit")==0)
    {
      exit(0);
    }

    pid=fork();
    switch(pid)
    {
      case -1: perror("Erreur fork.\n");
               exit(1);
      case  0: status = sh_execute ( args, forbiddens ) ;
    }
    // gestion non bloquante du wait
    memset(&actionfils,0, sizeof(actionfils));
    actionfils.sa_handler = gestionFils ;
    sigaction(SIGCHLD,&actionfils, NULL );
    sleep(1);
/* s h_ f r e e ( l i n e ) ; */
/* s h_ f r e e ( a r g s ) ; */
  } while ( status == 0 ) ;
}
