/** Fichier : client.c (Communication Sockets/UDP)
 *   Ce processus envoie un nombre à un processus distant qui l'incrémente
 *   et lui renvoie ...  */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>

int main(int argc, char *argv[]){
  int looptime = 0; /* Numero de la boucle */
  socklen_t ls = sizeof(struct sockaddr_in); /* Taille des adresses */
  /*---- Caracterisation de la socket d'émission ----------*/
  int sd0;                 /* Descripteur  */
  int ps0 = 0;    /* Port         */
  struct sockaddr_in adr0, *padr0 = &adr0; /* Adresse  */
  /*---- Caracterisation de la socket distante ------*/
  struct sockaddr_in adr1,*padr1 = &adr1;  /* Adresse du destinataire */
  /*---- Buffers pour Messages -------------------------------*/
  char msg_in[3] = "0";      /* Message recu de "0" a "99" */
  char msg_out[3] = "0";    /* Message a envoyer "0" a "99" */

  struct sockaddr_in adr2, *padr2 = &adr2;

  /* 0) Verifications de base */
  if (argc != 2){
    fprintf(stderr,"Syntaxe d'appel : a.out nom_du_host_\"peer\" \n");
    exit(2);
  }
  /* 1) Concernant la socket d'émission ================*/
  /* a) Creation : Domaine AF_INET, type DGRAM, proto. par defaut*/
  if ((sd0=socket(AF_INET, SOCK_DGRAM, 0)) == -1)
    perror("[SOCK_DGRAM, AF_INET, 0]");
  else
    printf("socket [SOCK_DGRAM, AF_INET, 0] creee\n");
  /* b) Preparation de l'adresse d'attachement */
  adr0.sin_family      = AF_INET;
  adr0.sin_addr.s_addr = htonl(INADDR_ANY);  /* Format reseau */
  adr0.sin_port        = htons(ps0);  /* Format reseau */
  /* c) Demande d'attachement de la socket */
  if(bind(sd0,(struct sockaddr *)(padr0),ls) == -1) {
    perror("Attachement de la socket impossible");
    close(sd0);  /* Fermeture de la socket               */
    exit(2);       /* Le processus se termine anormalement.*/
  }
  /* d) Recuperation de l'adresse effective d'attachement. */
  getsockname(sd0,(struct sockaddr *)padr0,&ls);
  /* 2) Concernant la socket de destination ================*/
  /* a) A partir du nom du destinataire */
/* Recuperation de l'adresse IP */
    inet_aton(argv[1],&(adr1.sin_addr));
    adr1.sin_family = AF_INET;
    adr1.sin_port   = htons(5001); /* Meme port que sd0 : why not ? */
    fprintf(stdout,"machine --> %s \n", inet_ntoa(adr1.sin_addr));

  /* 3) Boucle emission-reception
     a particulariser selon que l'on est le serveur ou le client ..*/
  printf("\n**********CLIENT***********\n");
  for(;;) {
    int i;
    /* a) Emission */
    printf("\n\nEnvoi(%d) ... ", looptime);
    if (sendto(sd0,msg_out,sizeof(msg_out),0,(struct sockaddr *)padr1,ls) >0)
      printf("termine : valeur = %s !\n",msg_out);
    else
      printf("inacheve : %s !\n",msg_out);
    /* b) Reception */
    printf("Attente de reception ... ");
    if (recvfrom(sd0,msg_in,sizeof(msg_in),0, (struct sockaddr *)NULL, NULL) == -1)
      printf("inachevee : %s !\n",msg_in);
    else  {
      printf("terminee : valeur = %s !\n",msg_in);
      i = atoi(msg_in);
      sprintf(msg_out,"%d",i);
    }
    printf("\n\t------------------\n");
    sleep(1);
    looptime++;
  }
}
