#include <iostream>
#include <fstream>
#include <cstring>
#include <fstream>
#include "huffman.h"

using namespace std;

int main()
{
	ifstream fin("input.txt");
	ofstream fout("output.txt");
	
	const int maxSize = 10000;
	
	if (fin.fail())
	{
		cout << "No input file";
		return 0;
	}	
	
	char s[maxSize] = {0};
	char temp[maxSize] = {0};
	
	while (fin.getline(temp, maxSize))
	{
		strcat(s, temp);
	}
	
	huffmanEncode(s, fout);	
	
	fin.close();
	fout.close();
	return 0;
}
