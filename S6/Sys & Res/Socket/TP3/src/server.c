/** Fichier : inc.c (Communication Sockets/UDP)
 *   Les deux processus distants s'envoient un nombre qu'ils
 *   incrementent successivement : L'un compte en pair, l'autre en impair ...  */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <unistd.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>

void calculate(char *op, float a, float b, char *msg_out);
void handle_zombies(int sig);

int main(int argc, char *argv[]){
  int looptime = 0; /* Numero de la boucle */
  socklen_t ls = sizeof(struct sockaddr_in); /* Taille des adresses */
  /*---- Caracterisation de la socket d'émission ----------*/
  int sd0;                 /* Descripteur  */
  int ps0 = 50002;    /* Port         */
  struct sockaddr_in adr0, *padr0 = &adr0; /* Adresse  */
  /*---- Caracterisation de la socket distante ------*/
  struct sockaddr_in adr1,*padr1 = &adr1;  /* Adresse du destinataire */
  /*---- Buffers pour Messages -------------------------------*/
  char msg_in[10];     /* Message recu de "0" a "99" */
  char msg_out[50];    /* Message a envoyer "0" a "99" */

  char* a;
  char* b;
  char *op;


  struct sigaction action ;

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

  memset(&action , 0, sizeof (action));
  action.sa_handler = handle_zombies ;
  sigaction(SIGCHLD , &action , NULL );

  /* 3) Boucle emission-reception */
  printf("\n**********SERVER : ON***********\n");
  for(;;) {
    /* a) Reception */
    printf("Attente de reception ...");
    if (recvfrom(sd0,msg_in,sizeof(msg_in),0, (struct sockaddr *)padr1, &ls) == -1){
      if(errno != EINTR){
        printf("inachevée : %s !\n",msg_in);
      }
    }
    else  {
      printf("terminée : valeur = %s !\n",msg_in);
      printf("Adresse src : %s\n", inet_ntoa(adr1.sin_addr));
      printf("Port src : %hu\n", ntohs(adr1.sin_port));

      op = strtok(msg_in, " ");
      a = strtok(NULL, " ");
      b = strtok(NULL, " ");

      /* Ouverture d'une session */
      if(strcmp(msg_in, "(") == 0){
        int sd1;
        float x;
        _Bool x_initialized = false;
        struct sockaddr_in adr2, *padr2 = &adr2;
        switch(fork()){
            case -1:
                perror("Erreur de création du fork");
                exit(1);
                break;
            case 0:
                /* Création d'une socket de session */
                if ((sd1=socket(AF_INET, SOCK_DGRAM, 0)) == -1){
                  perror("[SOCK_DGRAM, AF_INET, 0]");
                }else{
                  printf("\nCréation d'un socket pour une session...\n");
                }

                adr2.sin_family      = AF_INET;
                adr2.sin_addr.s_addr = htonl(INADDR_ANY);  /* Format reseau */
                adr2.sin_port        = htons(0);
                /* Attachement de la socket de session */
                if(bind(sd1,(struct sockaddr *)(padr2),ls) == -1) {
                    perror("Attachement de la socket pour une session impossible");
                    close(sd0);
                    exit(2);
                }
                /* Emission d'une message initial de la session */
                sprintf(msg_out,"%s","Ouverture d'une session" );
                if (sendto(sd1,msg_out,sizeof(msg_out),0,(struct sockaddr *)padr1,ls) >0)
                  printf("[SESSION] envoi terminé : valeur = %s !\n",msg_out);
                else
                  printf("[SESSION] envoi inacheve : %s !\n",msg_out);
                for(;;){
                    if (recvfrom(sd1,msg_in,sizeof(msg_in),0, (struct sockaddr *)padr1, &ls) == -1)
                        printf("[SESSION] réception inachevée : %s !\n",msg_in);
                    else  {
                        printf("[SESSION] réception terminée : valeur = %s !\n",msg_in);
                        printf("[SESSION] Adresse src : %s\n", inet_ntoa(adr1.sin_addr));
                        printf("[SESSION] Port src : %hu\n", ntohs(adr1.sin_port));
                        /* Si on réceptionne ")", alors on met fin à la session */
                        if(strcmp(msg_in,")") == 0){
                            break;
                        } else {
                        /* Sinon on découpe à la chaine, puis on identifie le type de la requête */
                            op = strtok(msg_in, " ");
                            a = strtok(NULL, " ");
                            b = strtok(NULL, " ");
                            /* Si création de variable */
                            if(strcmp(op,"=") == 0){
                                x = atof(b);
                                x_initialized = true;
                                sprintf(msg_out,"%f",x );
                                if (sendto(sd1,msg_out,sizeof(msg_out),0,(struct sockaddr *)padr1,ls) >0)
                                    printf("[SESSION] envoi terminé : valeur = %s !\n",msg_out);
                                else
                                    printf("[SESSION] envoi inachevé : %s !\n",msg_out);
                            /* Si calcul */
                            } else {
                                if((strcmp(a,"x") == 0) && (strcmp(b,"x") == 0)){
                                    if(x_initialized){
                                        calculate(op,x,x, msg_out);
                                    } else {
                                        sprintf(msg_out,"%s","Erreur : x non initialisé");
                                    }
                                } else if(strcmp(a,"x") == 0){
                                    if(x_initialized){
                                        calculate(op,x,atof(b), msg_out);
                                    } else {
                                        sprintf(msg_out,"%s","Erreur : x non initialisé");
                                    }
                                } else if(strcmp(b,"x") == 0){
                                    if(x_initialized){
                                        calculate(op,atof(a),x, msg_out);
                                    } else {
                                        sprintf(msg_out,"%s","Erreur : x non initialisé");
                                    }
                                } else {
                                    calculate(op,atof(a),atof(b),msg_out);
                                }

                                if (sendto(sd1,msg_out,sizeof(msg_out),0,(struct sockaddr *)padr1,ls) >0)
                                    printf("[SESSION] envoi terminé : valeur = %s !\n",msg_out);
                                else
                                    printf("[SESSION] envoi inachevé : %s !\n",msg_out);
                            }
                        }
                    }

                }
                sprintf(msg_out,"%s","Fermeture de la session" );
                if (sendto(sd0,msg_out,sizeof(msg_out),0,(struct sockaddr *)padr1,ls) >0)
                  printf("envoi terminé : valeur = %s !\n",msg_out);
                else
                  printf("envoi inachevé : %s !\n",msg_out);
                exit(0);
            }
      } else {
        calculate(op, atof(a), atof(b), msg_out);
        /* c) Emission */
        printf("\n\nEnvoi ... ");
        if (sendto(sd0,msg_out,sizeof(msg_out),0,(struct sockaddr *)padr1,ls) >0)
          printf("terminé : valeur = %s !\n",msg_out);
        else
          printf("inachevé : %s !\n",msg_out);
        printf("\n\t------------------\n");
      }
    }
  }
}

void calculate(char *op, float a, float b, char *msg_out){
  if(strcmp(op, "+") == 0){
    sprintf(msg_out,"%.2f", a + b);
  } else if(strcmp(op, "-") == 0){
    sprintf(msg_out,"%.2f", a - b);
  } else if(strcmp(op, "*") == 0){
    sprintf(msg_out,"%.2f", a * b);
  } else if(strcmp(op, "/") == 0){
    if(b == 0.0){
      strcpy(msg_out, "Division par 0 : infinité");
    } else {
      sprintf(msg_out,"%.2f", a / b);
    }
  } else {
    strcpy(msg_out, "Opérateur incorrect");
  }
}

void handle_zombies(int sig){
/* Handler du signal SIGCHLD */
  int exit_cond ;
  pid_t pid;
  pid = wait(& exit_cond );
  if (!WIFEXITED(exit_cond))
    printf ("La session %d s’est mal terminée : %d\n" , pid ,
    WTERMSIG(exit_cond));
}
