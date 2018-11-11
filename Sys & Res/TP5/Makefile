all : main.o id3_frame.o id3_tagheader.o util.o
	gcc main.o id3_frame.o id3_tagheader.o util.o -o id3

util.o : ./src/util.c 
	gcc -c ./src/util.c -I ./include

main.o : ./src/main.c
	gcc -c ./src/main.c -I ./include

id3_frame.o : ./src/id3_frame.c
	gcc -c ./src/id3_frame.c -I ./include

id3_tagheader.o : ./src/id3_tagheader.c
	gcc -c ./src/id3_tagheader.c -I ./include

clean:
	rm *.o id3

run :
	./id3 ./mp3/track1.mp3
