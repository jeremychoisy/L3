all : main.o wav.o
	gcc main.o wav.o -o WAV

main.o : ./src/main.c
	gcc -c ./src/main.c -I ./include

wav.o : ./src/wav.c
	gcc -c ./src/wav.c -I ./include

clean : 
	rm *.o *.wav *.csv WAV

run : 
	./WAV mel_fel1000Hz