#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <time.h>

#define BUF_SIZE 256
#define READ_END 0
#define WRITE_END 1

void make_message(int num, char *message){
  char buftime [26];
  time_t timer;
  struct tm* tm_info;
  time(&timer );
  tm_info = localtime(&timer );
  strftime(buftime , 26, "%Y-%m-%d %H:%M:%S" , tm_info ) ;
  sprintf(message , "%s %d at %s" , "Hello , I’m child number",num , buftime);
}

int main(int argc, char *argv[]){
  int i;

  int fd_1[2];
  int fd_2[2];


  if(pipe(fd_1) == -1){
    fprintf(stderr, " Pipe ␣ failed ");
    return 1;
  }

  if(pipe(fd_2) == -1){
    fprintf(stderr, " Pipe ␣ failed ");
  return 1;
  }

  switch (fork()){ /*----- child 1 */
  case -1:
    printf("Error forking child 1!\n");  exit(1);
  case 0:
    {
      char buf[BUF_SIZE];



      printf("\nChild 1 executing...\n");

      close(fd_1[READ_END]);
      close(fd_2[WRITE_END]);

      for(int i=0;i<10;i++){
          /*Child 1 writing in shared mem */
          make_message(1,buf);
          sleep(1); /* La fabrication du message prend un peu de temps */
          write(fd_1[WRITE_END],buf,BUF_SIZE);
          printf("Message sent by child 1: %s\n", buf);

          /*Child 1 reading from shared mem */
          read(fd_2[READ_END],buf,BUF_SIZE);
          printf("Message received by child 1: %s\n", buf);
      }

      close(fd_2[READ_END]);
      close(fd_1[WRITE_END]);

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

      printf("\nChild 2 executing...\n");

      close(fd_2[READ_END]);
      close(fd_1[WRITE_END]);

      /*Child 2 reading from shared memory*/
      for(int i=0;i<10;i++){
          read(fd_1[READ_END],buf,BUF_SIZE);
          printf("Message received by child 2: %s\n", buf);

          /*Child 2 writing in shared mem*/
          make_message(2,buf);
          sleep(1); /* La fabrication du message prend un peu de temps*/
          write(fd_2[WRITE_END],buf,BUF_SIZE);
          printf("Message sent by child 2: %s\n", buf);;
      }

      close(fd_1[READ_END]);
      close(fd_2[WRITE_END]);
      /*printf("Exiting child 2...\n");*/
      _exit(EXIT_SUCCESS);
    }
    break;
  default:
    break;
  }





  printf("Parent waiting for children completion...\n");
  for(i=0;i<2;i++){
    if (wait(NULL) == -1){
      printf("Error waiting.\n");
      exit(EXIT_FAILURE);
    }
  }
  printf("Parent finishing.\n");

  close(fd_1[READ_END]);
  close(fd_2[WRITE_END]);
  close(fd_2[READ_END]);
  close(fd_1[WRITE_END]);
  exit(EXIT_SUCCESS);

}
