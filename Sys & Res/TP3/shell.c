
#include <stdio.h>
#include <stdlib.h>
#include "function.h"
#include <signal.h>
#include <string.h>


int main ( int argc , char *argv [ ] ) {
// Init : Load config files, if any
// Interp : Run Command loop
  sh_loop ( ) ;
// Termin : Perform any shutdown / cleanup
  return EXIT_SUCCESS ;
}
