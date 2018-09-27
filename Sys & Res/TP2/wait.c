/** Fichier wait .c : création / terminaison propre d’un processus :
a) On crée un fils .
b) Le père attend la fin de celui -ci en testant la condition de sa fin.
On peut simuler une mauvaise fin en effectuant un ’kill ’ du
processus fils . */
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main (int argc , char * argv []) {
  int exit_cond ;
  pid_t pid;
  pid = fork () ;
  switch (pid)
  {
    case -1 : perror (" Erreur de création du processus ");
    exit (1) ;
    case 0 : /* Ce code s’exécute chez le fils */
      printf (" Pid du fils = %d\n" , getpid () );
      sleep (20) ; /* Duree de vie du fils */
      break ;
    default : /* Ce code s’exécute chez le père */
      printf (" Pid du pere = %d\n" , getpid () );
      printf (" Attente de la terminaison du fils ...\n");
      pid = wait (& exit_cond );
      if ( WIFEXITED ( exit_cond ))
      printf (" Le fils %d s ’ est termine correctement : %d\n " ,
      pid , WEXITSTATUS ( exit_cond ));
      else
      printf (" Le fils %d s ’ est mal termine : %d\n" , pid , WTERMSIG ( exit_cond ));
  } /* switch */
exit (0) ; /* exécuté par le fils et le père */
}
