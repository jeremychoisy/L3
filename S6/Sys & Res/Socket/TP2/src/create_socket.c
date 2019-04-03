#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>

int main(int argc, char *argv[]){
    /* socket(AF_UNIX, SOCK_STREAM, IPPROTO_TCP) */
    printf("\n[ AF_UNIX , SOCK_STREAM , IPPROTO_TCP ] :\n");
    if(socket(AF_UNIX, SOCK_STREAM, IPPROTO_TCP) == -1){
        perror("Echec: ");
    }
    else
    {
        printf("Socket cree\n");
    }

    /* socket(AF_INET, SOCK_STREAM, IPPROTO_TCP) */
    printf("\n[ AF_INET , SOCK_STREAM , IPPROTO_TCP ] :\n");
    if(socket(AF_INET, SOCK_STREAM, IPPROTO_TCP) == -1){
        perror("Echec: ");
    }
    else
    {
        printf("Socket cree\n");
    }

    /* socket(AF_INET, SOCK_DGRAM , 0) */
    printf("\n[ AF_INET , SOCK_DGRAM , 0 ] :\n");
    if(socket(AF_INET, SOCK_DGRAM , 0) == -1){
        perror("Echec: ");
    }
    else
    {
        printf("Socket cree\n");
    }

    /* socket(AF_INET, SOCK_RAW, IPPROTO_TCP) */
    printf("\n[ AF_INET , SOCK_RAW , IPPROTO_TCP] :\n");
    if(socket(AF_INET, SOCK_RAW, IPPROTO_TCP) == -1){
        perror("Echec: ");
    }
    else
    {
        printf("Socket cree\n");
    }

    /* socket(AF_X25, SOCK_SEQPACKET, 0) */
    printf("\n[ AF_X25, SOCK_SEQPACKET, 0 ] :\n");
    if(socket(AF_X25, SOCK_SEQPACKET, 0) == -1){
        perror("Echec: ");
    }
    else
    {
        printf("Socket cree");
    }

    /* socket(AF_INET, SOCK_RDM, 0) */
    printf("\n[ AF_INET, SOCK_RDM, 0 ] :\n");
    if(socket(AF_INET, SOCK_RDM, 0) == -1){
        perror("Echec: ");
    }
    else
    {
        printf("Socket cree\n");
    }
}
