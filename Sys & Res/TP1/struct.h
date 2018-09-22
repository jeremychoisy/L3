/* Structure de données dataConcession permettant d'envoyer des variables
de type différents aux threads concession :
- le numéro de concession
- le pointeur vers le stock disponible */
typedef struct{
  int num;
  int * pstock;
}dataConcession;
