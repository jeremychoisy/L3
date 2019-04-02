#include <stdio.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <arpa/inet.h>

#include "mysocket.h"

int main(int argc, char *argv[]){
    int sd_1, sd_2;
    struct sockaddr_in p_address_1, p_address_2;

    if(sd_1 = create_socket(SOCK_STREAM, 0, &p_address_1) == -1){
        perror("Echec : ");
    }

    if(sd_2 = create_socket(SOCK_DGRAM, 2467, &p_address_2) == -1){
        perror("Echec : ");
    }

    printf("Socket UPD attaché à l'interface : %s\n", inet_ntoa(p_address_2.sin_addr));
    printf("Socket UPD attaché au port : %hu\n", ntohs(p_address_2.sin_port));
    printf("Socket TCP attaché à l'interface : %s\n", inet_ntoa(p_address_1.sin_addr));
    printf("Socket TCP attaché au port : %hu\n", ntohs(p_address_1.sin_port));

    sleep(60);
}
