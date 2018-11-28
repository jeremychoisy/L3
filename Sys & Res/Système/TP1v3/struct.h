/* Structure de données dataConcession permettant d'envoyer des variables
de type différents aux threads conrrespondants : */

typedef struct{
  int num;
  int * pstock;
}dataConcession;

typedef struct{
  pthread_mutex_t *pmutex;
  int * pstock;
}dataUE;
