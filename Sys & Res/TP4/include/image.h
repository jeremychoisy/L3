#include <stdio.h>

#ifndef IMAGEH
#define IMAGEH

#define SIZECOMMENT 5000

typedef struct {
  char nm[5];  /* Nombre magique */
  char comments[SIZECOMMENT];
  int nl, nc;  /* Nombre ligne/colonnes */
  int ng;      /* Nombre de gris */
  unsigned char *pixels; /* valeurs de pixels */
} image;

/* Prototypes */
image *lecture_image(FILE *f);
void ecriture_image(FILE *f, image *);

void inverse_image(image *); /* Im[i] = MaxLevel - Im[i] */

#endif
