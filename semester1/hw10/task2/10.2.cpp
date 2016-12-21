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

	huffmanDecode(fin, fout);

	fin.close();
	fout.close();
	return 0;
}
