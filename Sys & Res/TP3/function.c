
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

/*======================================================*/
char * sh_read_line ( void ) {
  char * line = NULL ;
  ssize_t bufsize = 0 ; // donc getline realise l’allocation
  getline(&line , &bufsize , stdin ) ;
  return line ;
}

/*======================================================*/
# define LSH_TOK_BUFSIZE 64
# define LSH_TOK_DELIM "␣\t\n"

char ** sh_split_line ( char * line ) {
  int bufsize = LSH_TOK_BUFSIZE , position = 0 ;
  char ** tokens = malloc ( bufsize * sizeof ( char * ) ) ;
  char * token ;
  if ( ! tokens ) {
      fprintf ( stderr , " lsh :␣ allocation ␣ error \n" ) ;
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
        fprintf ( stderr , " lsh :␣ allocation ␣ error \n" ) ;
        exit ( EXIT_FAILURE ) ;
      }
    }
    token = strtok ( NULL , LSH_TOK_DELIM ) ;
  }
    tokens [ position ] = NULL ;
    return tokens ;
}

/*======================================================*/
int sh_execute ( char ** args ) {
  char *str=malloc(sizeof(char)*strlen(args[0]));
  strcpy(str,"/bin/");
  execv(strcat(str,args[0]),args);
//  free(str);
}

/*======================================================*/
void sh_loop ( void ) {
  char *prompt = " l3miage ␣ shell ␣ >␣ " ;
  char *line ;
  char **args ;
  int status;
  pid_t pid;
  int exit_cond;
  do {
    printf ( "%s  " , prompt ) ;
    fflush ( stdout ) ;
    line = sh_read_line ( ) ;
    args = sh_split_line ( line ) ;
    pid=fork();
    if(pid==0)
    {
      status = sh_execute ( args ) ;
    }
    else
    {
      pid=wait(&exit_cond);
      if ( WIFEXITED ( exit_cond ))
        printf (" Le fils %d s ’ est termine correctement : %d\n " , pid ,WEXITSTATUS ( exit_cond ));
      else
        printf (" Le fils %d s ’ est mal termine : %d\n" , pid , WTERMSIG ( exit_cond ));
    }
/* s h_ f r e e ( l i n e ) ; */
/* s h_ f r e e ( a r g s ) ; */
  } while ( status ) ;
}
