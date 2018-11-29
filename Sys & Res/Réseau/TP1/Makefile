all : main.o queue.o
	gcc main.o queue.o -o SED

main.o : ./src/main.c
	gcc -c ./src/main.c -I ./include

queue.o : ./src/queue.c
	gcc -c ./src/queue.c -I ./include

clean : 
	rm *.o SED

run : 
	./SED 100 1.25 1.00