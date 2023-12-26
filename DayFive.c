#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LINES 1000
#define MAX_DIGITS 20

char file[MAX_LINES][MAX_DIGITS];
int fileLength = 0;

long FromSeedToLocation(long seed);

int compare(const void *a, const void *b);

int main() {
    FILE *filePtr = fopen("input5", "r");

    if (filePtr == NULL) {
        perror("Error opening file");
        return 1;
    }

    while (fileLength < MAX_LINES && fgets(file[fileLength], MAX_DIGITS, filePtr) != NULL) {
        fileLength++;
    }

    fclose(filePtr);

    long locations[MAX_LINES];
    char *token = strtok(file[0], " ,\t\n");

    int index = 0;
    while (token != NULL && index < MAX_LINES) {
        locations[index++] = atol(token);
        token = strtok(NULL, " ,\t\n");
    }

    for (int i = 0; i < fileLength; i++) {
        locations[i] = FromSeedToLocation(locations[i]);
    }

    qsort(locations, fileLength, sizeof(long), compare);

    printf("Part 1: %ld\n", locations[0]);

    return 0;
}

long FromSeedToLocation(long seed) {
    for (int i = 3; i < fileLength; i++) {
        char *token;
        char *line = file[i];
        bool reachedEOF = i >= fileLength - 1;

        token = strtok(line, " ,\t\n");
        long destinationStart = atol(token);

        token = strtok(NULL, " ,\t\n");
        long sourceStart = atol(token);

        token = strtok(NULL, " ,\t\n");
        long range = atol(token);

        while (token != NULL && !reachedEOF) {
            if (seed == sourceStart) {
                seed = destinationStart + (seed - sourceStart);

                while (1) {
                    i++;
                    if (i >= fileLength - 1) {
                        break;
                    }
                }

                break;
            }

            if (i + 1 == fileLength) {
                reachedEOF = 1;
                break;
            }

            i++;

            char *nextLine = file[i];

            if (nextLine == NULL) {
                break;
            }

            token = strtok(nextLine, " ,\t\n");
            if (token == NULL) {
                break;
            }

            destinationStart = atol(token);

            token = strtok(NULL, " ,\t\n");
            if (token == NULL) {
                break;
            }

            sourceStart = atol(token);

            token = strtok(NULL, " ,\t\n");
            if (token == NULL) {
                break;
            }

            range = atol(token);
        }

        if (reachedEOF) {
            return seed;
        }
    }

    return seed;
}

int compare(const void *a, const void *b) {
    return (*(long *)a - *(long *)b);
}

