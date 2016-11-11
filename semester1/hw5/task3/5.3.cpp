#include <fstream>
#include <iostream>
#include <cstring>

using namespace std;

const char badSymbol = -1;

bool beginning(char *string, int length, int index, char c1, char c2)
{
	if (c2 == badSymbol)
	{
		return string[index] == c1;
	}
	
	return index < length - 1 && string[index] == c1 && string[index + 1] == c2;
}

bool beginningOfComment(char *string, int length, int index)
{
	return beginning(string, length, index, '/', '*');
}

bool beginningOfString(char *string, int length, int index)
{
	return beginning(string, length, index, '"', badSymbol);
}

bool beginningOfNeededComment(char *string, int length, int index)
{
	return beginning(string, length, index, '/', '/');
}

bool endOfComment(char *string, int length, int index)
{
	return beginning(string, length, index, '*', '/');
}

void processInComment(char *string, int length, int &index, bool &inComment)
{
	if (endOfComment(string, length, index))
	{
		inComment = false;
		index += 2;
	}
	else
	{
		index++;
	}
}

bool endOfString(char *string, int length, int index)
{
	return beginningOfString(string, length, index);
}

void processInString(char *string, int length, int &index, bool &inString)
{
	if (endOfString(string, length, index))
	{
		inString = false;
	}
	index++;
}

void printSuffix(char *string, int length, int index, ofstream &fout)
{
	while (index < length)
	{
		fout << string[index];
		index++;
	}
	fout << '\n';
}

void processString(char *string, int length, bool &inComment, ofstream &fout)
{
	char current = 0;
	int index = 0;
	bool inString = false;
	while (index < length)
	{
		current = string[index];
		if (inComment)
		{
			processInComment(string, length, index, inComment);
			continue;
		}
		
		if (inString)
		{
			processInString(string, length, index, inString);
			continue;
		}
		
		if (beginningOfComment(string, length, index))
		{
			inComment = true;
			index += 2;
			continue;
		}
		
		if (beginningOfString(string, length, index))
		{
			inString = true;
			index += 2;
			continue;
		}
		
		if (beginningOfNeededComment(string, length, index))
		{
			printSuffix(string, length, index, fout);
			break;
		}
		
		index++;
	}
}

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	
	if (fin.fail())
	{
		cout << "There's no input file" << '\n';
		return 0;
	}
	
	const int maxSize = 1000;
	
	bool inComment = false;
	char currentString[maxSize] = {0};
	
	while (fin.getline(currentString, maxSize))
	{
		processString(currentString, strlen(currentString), inComment, fout);
	}
	
	fin.close();
	fout.close();
	return 0;
}