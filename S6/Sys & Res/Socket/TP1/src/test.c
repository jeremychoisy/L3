/*--------------------------------------------------
   Version 0 d'un échange SHM System V entre deux fils.
    Les semaphores utilisés sont POSIX !

    Author : GM
 ----------------------------------------------------*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/sem.h>
#include <semaphore.h>
#include <sys/shm.h>
#include <time.h>
#define BUF_SIZE 256



int main(int argc, char *argv[]){
  int i;
  char *virtualaddr;
  int shmid;
  sem_t *s_get,*s_put;
  key_t key;
  
  /*----- Attaching the shared mem to my address space  */
  key = ftok(argv[0],'R'); /* Generation de la key */
  shmid = shmget(key, 1024, 0644|IPC_CREAT); /* Creation du segment
                                                memoire : 1024 octets */
  if (0 > shmid){
    perror("Shared Mem creation error\n");
    exit(1);  
  }
  /* => virtualaddr will be available across fork ! */
  virtualaddr = shmat(shmid, (void*)0, 0); /* Attachement à l'espace mem du processus */

  /*--- Create POSIX Named Semaphores, and initialising with 1 */
  int init_sem_value = 1; /* Dijkstra sem */
  s_put = sem_open("/put", O_CREAT|O_RDWR, 0644, init_sem_value);

  switch (fork()){ /*----- child 1 */
  case -1:
    printf("Error forking child 1!\n");  exit(1);
  case 0:
    {
      char buf[BUF_SIZE];

      /* Referring the semaphore */
      s_put = sem_open ("/put", O_RDWR);

        for(int i=0;i<10;i++){
            sem_wait(s_put);
            printf("lol");
            sem_post(s_put);
        }

      /*printf("Exiting child 1...\n"); */
      _exit(0);    
    }
    break;
  default: break;
  }



  
  switch (fork()){ /*----- child 2 */
  case -1:
    printf("Error forking child 2!\n"); exit(1);
  case 0:
    {
      char buf[BUF_SIZE];

      /* Referring the semaphore */
      s_put = sem_open ("/put", O_RDWR);

        for(int i=0;i<10;i++){
            sem_wait(s_put);
            printf("mdr");
            sem_post(s_put);
        }

      /*printf("Exiting child 2...\n");*/
      _exit(EXIT_SUCCESS);
    }
    break;  
  default:
    break;
  }



  
  
  printf("Parent waiting for children completion...\n");
  for(i=0;i<=1;i++){
    if (wait(NULL) == -1){
      printf("Error waiting.\n");
      exit(EXIT_FAILURE);
    }
  }
  printf("Parent finishing.\n");

  //Deleting semaphores..
  sem_unlink ("/put");


  //Deleting Shared Memory.
  shmctl (shmid, IPC_RMID, NULL);
  exit(EXIT_SUCCESS);

}