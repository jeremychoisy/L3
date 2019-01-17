#include <stdio.h>


int challenge_1() {
	int res;
	while(fscanf(stdin,"%d",&res)==1 && res!=42){
		fprintf(stdout,"%d\n",res);
	}
	return 0;
}


int challenge_2() {
	int res;
	while(fscanf(stdin,"%d",&res) ==1){
		fprintf(stdout,"%d\n",res);
	}
	return 0;
}
