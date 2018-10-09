#include <stdio.h>
#include <ctype.h>
#include <iostream>
#include <stdbool.h>

char* Whitespace(char* cPtr)
{
	if (!cPtr) return cPtr;
	
	while(isspace(*cPtr))
	{
		cPtr++;
	}
	return cPtr;
}

//Checks to see if current character in the string is equal to a given character passed as "c"
//If it is equivalent to "c", then the char* cPtr is incremented to skip over the character
//Else, it returns char* cPtr = NULL;
//Essentially, it skips any character "c" which is passed to it
char* Character(char* cPtr, char c)
{
	if (!cPtr) return cPtr;
	
	cPtr = Whitespace(cPtr);
	
	if (*cPtr != c)
	{
		return NULL;
	}
	else
	{
		return ++cPtr;
	}
}

char* Word(char* cPtr, char* word, bool* endOfSentence, bool* endOfFile)
{
	if (!cPtr) return cPtr;
	
	cPtr = Whitespace(cPtr);
	
	while(isalnum(*cPtr))
	{
		*word++ = *cPtr++;
	}
	if (*cPtr == '.')
	{
		cPtr++;
		*endOfSentence = true;
	}
	if (*cPtr == '+')
	{
		*endOfSentence = true;
		*endOfFile = true;
	}
	
	
	*word = '\0'; //sets the last char to the standard c delimiter
	return cPtr;
}

char* GetWordsInSentence(char* cPtr, char* word1, bool* endOfSentence, bool* endOfFile)
{
	cPtr = Character(cPtr, '|');
	cPtr = Word(cPtr, word1, endOfSentence, endOfFile);
    return cPtr;
}



main (int argc, char* argv[]) 
{
	//char sp = ' ';
	//std::cout << isalnum(sp) << std::endl;
	
	//typedef enum { false, true } bool;
	
	//Because strings are arrays of characters, in order to have a 2D array of strings
	//I have to create a 3D array of characters.
	char wordArray[5][25][15]; //5 Sentences, 25 Words per Sentence MAX, 15 Characters per Word MAX
	int jaggedArrayLength[5];
	int i = 0; //Sentence counter
	int j = 0; //Word counter
	int k = 0; //Character counter
	int jaggedLength = 0;
	bool endOfSentence = false;
	bool endOfFile = false;
	
	//Test string (needs to be turned into multiple sentences)
	char str[] = "|hello|hi|how|are|you.|this|is| the| second |sentence. |hella|ha|haw|ya|yeet. |this|is| the fourth |sentence. |hello|hi|how|are|five+.";
	
	//Pointer to current location in string
	char* cPointer = &str[0]; 
	
	while (i < 5 && endOfFile == false)
	{
		while (j < 25 && endOfSentence == false)
		{
			char* wordBeingIndexed = &wordArray[i][j][0];
			cPointer = GetWordsInSentence(cPointer, wordBeingIndexed, &endOfSentence, &endOfFile);
			j++;
		}
		std::cout << j << " + " << endOfSentence << std::endl;
		jaggedArrayLength[i] = j;
		j = 0;
		i++;
		endOfSentence = false;
	}
	
	i = 0;
	j = 0;
	
	while (i < 5)
	{
		std::cout << "\nSentence " << i + 1 << ": \n\n" << std::endl;
		
		while (j < jaggedArrayLength[i])
		{
			std::cout << "word " << j+1 << " is " << wordArray[i][j] << std::endl;
			j++;
		}
		j = 0;
		i++;
		
		
	}
	
	return 0; //end of main method
}







