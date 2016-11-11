#include <iostream>
#include <fstream>
#include <cstring>

using namespace std;

bool isNotEmpty(char *string)
{
	bool result = false;
	int length = strlen(string);
	for (int i = 0; i < length; i++)
	{
		if (string[i] != ' ' && string[i] != '\t')
		{
			result = true;
		}		
	}
	return result;
}

int main()
{
	ifstream cin("input.txt");
	
	if (cin.fail())
	{
		cout << "No input file" << '\n';
		return 0;
	}
	
	const int maxSize = 1000;
	
	char string[maxSize] = {0};
	int notEmptyCount = 0;
	
	while (cin.getline(string, maxSize))
	{
		if (isNotEmpty(string))
		{
			notEmptyCount++;
		}
	}
	
	cout << "Number of not empty string(s): ";
	cout << notEmptyCount << '\n';
	
	cin.close();	
	return 0;
}