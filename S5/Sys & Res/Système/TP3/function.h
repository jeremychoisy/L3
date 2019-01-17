void sh_loop ( void ) ;
int sh_execute ( char ** args, char ** forbiddens ) ;
char ** sh_split_line ( char * line );
char * sh_read_line ( void );
void handlerSIGINT(int sig);
void handlerSIGCHLD(int sig);
