
#include <stdio.h>
#include <stdlib.h>
#include "function.h"
#include <signal.h>
#include <string.h>

/*======================================================*/
// Handler du signal SIGINT
void handlerSIGINT(int sig){
  printf("Utilisation de Ctrl+C interdite, utilisez la commande exit.\n");
}

/*======================================================*/
int main ( int argc , char *argv [ ] ) {
// Init : Load config files, if any
  struct sigaction action;
// On met en place la gestion du signal SIGINT (CTRL+C)
  memset(&action,0,sizeof(action));
  action.sa_handler = handlerSIGINT;
  action.sa_flags=0;
  sigemptyset(&action.sa_mask);
  sigaction (SIGINT,&action,NULL);

// Interp : Run Command loop
  sh_loop ( ) ;
// Termin : Perform any shutdown / cleanup
  return EXIT_SUCCESS ;
}
