/* -- ----- ----- ----- ----- ----- ---- ----- ----- ----- ----- ----- ----- ----
* PROGRAM fork1 .c
* This program runs two processes , a parent and a child .
* Note that printf () is used in this program for simplicity . */
# include <stdio.h>
# include <sys/types.h>
#include <unistd.h>

# define MAX_COUNT 200

void ChildProcess ( void ); /* child process prototype */
void ParentProcess ( void ); /* parent process prototype */

int main ( void ){
  pid_t pid;
  pid = fork () ;
  if (pid == 0)
    ChildProcess () ;
  else
    ParentProcess () ;
    return 0;
}

void ParentProcess ( void ){
  int i;
  for (i = 1; i <= MAX_COUNT ; i++)
    printf (" This line is from parent , value = %d\n" , i);
    printf (" *** Parent is done ***\n ");
}

void ChildProcess ( void ){
  int i;
  for (i = 1; i <= MAX_COUNT ; i++)
    printf (" This line is from child , value = %d\n" , i);
    printf (" *** Child process is done ***\n");
}
