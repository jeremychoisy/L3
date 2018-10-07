
#include <stdio.h>
#include <stdlib.h>
#include "function.h"


int main ( int argc , char *argv [ ] ) {

// Init : Load config files, if any
// Interp : Run Command loop
  sh_loop ( ) ;
// Termin : Perform any shutdown / cleanup
  return EXIT_SUCCESS ;
}
