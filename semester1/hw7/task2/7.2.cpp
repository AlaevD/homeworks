#include <iostream>
#include <fstream>
#include "tree.h"

using namespace std;

int main()
{
	ifstream fin("input.txt");
	
	if (fin.fail())
	{
		cout << "No input file" << '\n';
		return 0;
	}
	
	Tree *tree = createTree();
	readTree(tree, fin);
	
	printInfix(tree);
	
	cout << " = ";
	cout << calculateValue(tree) << '\n';
	
	destroy(tree);
	fin.close();
	return 0;
}
