//Cameron Johnson
// id:1384841
// pa2
// ListClient.c
// Cmps 101 Winter 2015

#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#include "List.h"

#define BUFFER 160

// sorting algorithm
List sortList(char** array, int length){
	List sortedList = newList();
	prepend(sortedList, 0);
	for(int i = 1; i<length;i++){
		moveTo(sortedList,0);
		char *word = array[i];
		char *cur = array[getElement(sortedList)];
		//sorts array
		while(strcmp(word, cur)>0){
			moveNext(sortedList);
			if(getElement(sortedList) == -1) break;
			cur = array[getElement(sortedList)];
		}
		if(getIndex(sortedList) == -1){
			append(sortedList,i);
		}else{
			insertBefore(sortedList,i);
		}
	}
	return sortedList;
}


// increases the strings length by 1
char *strdup (const char *x) {
  char *f = malloc(strlen(x) + 1);
  if (f== NULL) return NULL;
  strcpy(f,x);
  return f;
}

//reads the file into an array of strings
char** read(FILE* input, int length){
  char** array = malloc(sizeof(char**) * length);
  char line[BUFFER];
  for(int i = 0; i < length; i++){
    fgets(line, BUFFER, input);
    array[i] = strdup(line);
  }
  return array;
}

//frees all the malloced heap space used by the program
void freeall(char** array, int count, List list){
  for (int i = 0; i < count; i++){
    free(array[i]);
  }
  free(array);
  freeList(&list);
}
// runs the main operation and sorting for the program
int main(int argc, char* argv[]){
  int length = 0;
  char line[BUFFER];
// checks to see if there are arguements for the input and output file
  if(argc != 3) {
    printf("Invalid number of files to work with");
    exit(1);
  }
  
  FILE* IN = fopen(argv[1], "r");
  FILE* OUT = fopen(argv[2], "w");
	// checks to see if the In and out files are properly done
  if(IN== NULL){ 
    printf("Cannot open %s for reading input\n", argv[1]);
    exit(1);
  } else if (OUT == NULL){
    printf("Cannot open file %s for writing output\n", argv[2]);
    exit(1);
  }

   // checks for how many words are in the input
  while( fgets(line, BUFFER, IN) != NULL) {
    length++;
  }

 // closes the input file and then reopens it
  fclose(IN);
  IN = fopen(argv[1], "r");
  
  // Reads in the input into an array of strings
  char** Strings = read(IN, length);

  // carries out insertion sort
  List sortedList = sortList(Strings, length);

  // prints out the list by looking for indices in array
  for(moveTo(sortedList, 0); getIndex(sortedList) >= 0; moveNext(sortedList)){
    fprintf(OUT, "%s", Strings[getElement(sortedList)]);
  } 

 // closes the in and output files
  fclose(IN);
  fclose(OUT);
  // frees the array, completely and then frees the list
  freeall(Strings, length, sortedList);
  return(0);
}
