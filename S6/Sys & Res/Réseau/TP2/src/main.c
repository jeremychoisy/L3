#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <limits.h>
#include <math.h>
#include <string.h>
#include "wav.h"

#define M_PI 3.14159265358979323846

/*Generate one pure tone: a sinusoidal waveform at a frequency and a amplitudes*/
int generate_sinusoid(double frequency,
                      double amplitude,
                      int32_t SampleRate,
                      int32_t FrameCount,
                      int16_t *buffer_p)
{
    int ret = 0;
    double SampleRate_d = (double)SampleRate;

    int32_t k;
    int16_t wave;
    /*Check for the violation of the Nyquist limit*/
    /*   if (frequency*2 >= SampleRate_d)
    {
        ret = -1;
        goto error0;
    }*/

    for (k = 0;
         k < FrameCount;
         k++)
    {
        wave = (int16_t)(amplitude * sin(k * frequency * 2.0 * M_PI / SampleRate_d));
        buffer_p[k] = wave;
    }
    return ret;
    /*
error0:
    return ret;*/
}

// transforme une mélodie codée sur 16 bits en mélodie codée sur 4 bits, tout en conservant l'échelle
int quantification4bits(int16_t *buffer_p,int size){
    int ret = 0;
    for(int i=0;i<size;i++){
        if(buffer_p[i]>= -32768 && buffer_p[i]<-28672){
            buffer_p[i] = -30720;
        }
        else if(buffer_p[i]>= -28672 && buffer_p[i]<-24576){
            buffer_p[i] = -26624;
        }
        else if(buffer_p[i]>= -24576 && buffer_p[i]<-20480){
            buffer_p[i] = -22528;
        }
        else if(buffer_p[i]>= -20480 && buffer_p[i]<-16384){
            buffer_p[i] = -18432;
        }
        else if(buffer_p[i]>= -16384 && buffer_p[i]<-12288){
            buffer_p[i] = -14336;
        }
        else if(buffer_p[i]>= -12288 && buffer_p[i]<-8192){
            buffer_p[i] = -10240;
        }
        else if(buffer_p[i]>= -8192 && buffer_p[i]<-4096){
            buffer_p[i] = -6144;
        }
        else if(buffer_p[i]>= -4096 && buffer_p[i]<0){
            buffer_p[i] = -2048;
        }
        else if(buffer_p[i]>= 0 && buffer_p[i]<4096){
            buffer_p[i] = 2048;
        }
        else if(buffer_p[i]>= 4096 && buffer_p[i]<8192){
            buffer_p[i] = 6144;
        }
        else if(buffer_p[i]>= 8192 && buffer_p[i]<12288){
            buffer_p[i] = 10240;
        }
        else if(buffer_p[i]>= 12288 && buffer_p[i]<16384){
            buffer_p[i] = 14336;
        }
        else if(buffer_p[i]>= 16384 && buffer_p[i]<20480){
            buffer_p[i] = 18432;
        }
        else if(buffer_p[i]>= 20480 && buffer_p[i]<24576){
            buffer_p[i] = 22528;
        }
        else if(buffer_p[i]>= 24576 && buffer_p[i]<28672){
            buffer_p[i] = 26624;
        }
        else if(buffer_p[i]>= 28672 && buffer_p[i]<32768){
            buffer_p[i] = 30720;
        }
    }
    return ret;
}
int generate_melody(double *frequencies,
                      double amplitude,
                      int32_t SampleRate,
                      int32_t FrameCount,
                      int16_t *buffer_p,
                      int number_of_notes
                      )
{
    int ret = 0;
    double SampleRate_d = (double)SampleRate;

    int32_t k;
    int16_t wave;
    /*Check for the violation of the Nyquist limit*/
    /*   if (frequency*2 >= SampleRate_d)
    {
        ret = -1;
        goto error0;
    }*/
    k = 0;
    for(int i =0;i<number_of_notes;i++){
        while(k < ((FrameCount/number_of_notes)*i))
        {
            wave = (int16_t)(amplitude * sin(k * frequencies[i] * 2.0 * M_PI / SampleRate_d));
            buffer_p[k] = wave;
            k++;
        }
    }
    return ret;
    /*
error0:
    return ret;*/
}

// Write audio samples in a CSV file
size_t write_CSV_file(char *filename, int32_t FrameCount,
                      int16_t *buffer_p)
{
    size_t ret = 0;
    FILE *csvfile;

    csvfile = fopen(strcat(filename, "_samples.csv"), "w");
    for (int32_t n = 0; n < FrameCount; n++)
    {
        ret += fprintf(csvfile, "%d\n", buffer_p[n]);
    }
    fclose(csvfile);
    return ret;
}

int main(int argc, char *argv[])
{
    int ret;
    FILE *file_p;
    char *filename;
    double mel[16] = {264, 297, 330, 352, 396, 440, 495, 528, 594, 660, 704, 792, 880, 990, 1056, 0};
    double frequency = 440; /*LA_3*/

    /* 16 bits / sample */
    double amplitude = 1 * (double)SHRT_MAX; // (16 bits)

    double duration = 8; /*seconds*/
    int32_t FrameCount = duration * SAMPLE_RATE;

    int16_t *buffer_p = NULL;
    size_t written;

    /* Verification de l'appel du programme ----*/
    if (argc != 2)
    { /* usage -- print usage message and exit */
        fprintf(stderr, "Usage: %s filename \n", argv[0]);
        exit(1);
    }
    else
        filename = argv[1];

    char filename_cp[20];
    strcpy(filename_cp, filename);
    /*Open the wav file*/
    file_p = fopen(strcat(filename_cp, ".wav"), "w");
    if (NULL == file_p)
    {
        perror("fopen failed in main");
        ret = -1;
        goto error0;
    }

    /*Allocate the data buffer*/
    buffer_p = allocate_PCM16_buffer(FrameCount);
    if (NULL == buffer_p)
    {
        perror("allocate_PCM16_buffer failed in main");
        ret = -1;
        goto error1;
    }

    /*Fill the buffer*/
    /*ret = generate_sinusoid(frequency,
                            amplitude,
                            SAMPLE_RATE,
                            FrameCount,
                            buffer_p);*/
    
    ret = generate_melody(mel,amplitude,SAMPLE_RATE,FrameCount,buffer_p,16);
    if (ret < 0)
    {
        fprintf(stderr, "generate_melody failed in main\n");
        ret = -1;
        goto error2;
    }

   ret = quantification4bits( buffer_p, FrameCount);
        if (ret < 0)
    {
        perror("quantification4bits failed in main");
        ret = -1;
        goto error2;
    }

    ret = write_PCM16_header(file_p, SAMPLE_RATE, FrameCount);
    if (ret < 0)
    {
        perror("write_PCM16_header failed in main");
        ret = -1;
        goto error2;
    }

    ret = write_PCM16wav_data(file_p,FrameCount,buffer_p);
    if (ret < 0)
    {
        perror("write_PCM16wav_data failed in main");
        ret = -1;
        goto error2;
    }

    // Print the sample values in the buffer to a CSV file
    ret = write_CSV_file(filename, FrameCount, buffer_p);
    if (ret < 0)
    {
        perror("write_CSV_file failed in main");
        ret = -1;
        goto error2;
    }

    /*Free and close everything*/
error2:
    free(buffer_p);
error1:
    fclose(file_p);
error0:
    return ret;
}