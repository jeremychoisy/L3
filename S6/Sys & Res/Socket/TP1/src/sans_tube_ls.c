#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define READ_END 0
#define WRITE_END 1
#define BUF_SIZE 1024


int main (int argc , char * argv[] ) {
    int status;
    int pfd[2];
    char buf[BUF_SIZE];

    if(pipe(pfd) == -1){
        perror("Echec : création tube.");
    }

    char cmd [] = "ls";
    char options [] = "-l";
    if (fork () == 0){
        close(pfd[READ_END]);

        dup2(pfd[WRITE_END],STDOUT_FILENO);
        close(pfd[WRITE_END]);

        execlp( cmd, cmd, options ,NULL ,NULL );

        exit(2);
    }
    else {
        close(pfd[WRITE_END]);
        read(pfd[READ_END],buf,BUF_SIZE);
        printf("%s\n",buf);
        close(pfd[READ_END]);
        wait(&status);

        return EXIT_SUCCESS ;
    }
}
