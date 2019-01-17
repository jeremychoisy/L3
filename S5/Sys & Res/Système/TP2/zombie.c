/** Fichier zombie .c : Faire mourir le fils avant le pere .
Et ce dernier ne s’en preoccupe pas ! */
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main (int argc , char * argv []) {
  int pid;
  switch (pid = fork () )
  {
    case -1: /* Pb ... */
    perror (" Erreur du fork ");
    exit (1) ;
    case 0: /* Ce code s’execute chez le fils */
      printf (" Je suis le fils : PID %d et le PID de mon pere est %d \n" ,
      getpid () , getppid () );
      printf (" Je suis le fils et je meurs : PID %d \n" ,
      getpid () );
      break ;
    default : /* Ce code s’execute chez le pere */
      printf ("\nJe suis le pere : PID %d \n" , getpid () );
      printf (" Qu ’ est devenu mon fils % d ?\n" , pid);
      printf (" Vous avez 30 sec pour lancer un "" ps -e -f"" et constater qu ’il est zombi !\n");
      sleep (30) ;
      printf (" Je suis le pere , et je meurs : PID % d\n" , getpid () );
  }
  printf ("\n");
  exit (0) ;
}
