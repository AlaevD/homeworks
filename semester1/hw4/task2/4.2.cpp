#include <fstream>
#include <cstring>

using namespace std;

void clear(bool *was, int length)
{
	for (int i = 0; i < length; i++)
	{
		was[i] = false;
	}
}

bool letter(char c)
{
	return 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z';
}

bool isLower(char c)
{
	return 'a' <= c && c <= 'z';
}

char toLower(char c)
{
	if (isLower(c))
	{
		return c;
	}
	return c - 'A' + 'a';
}

const int alphabetSize = 26;

void processString(char *string, int length, ofstream &cout)
{
	int currentIndex = 0;
	char currentSymbol = 0;
	
	bool was[alphabetSize] = {0};
	
	while (currentIndex < length)
	{
		currentSymbol = string[currentIndex];
		
		if (!letter(currentSymbol))
		{
			cout << currentSymbol;
			currentIndex++;
			continue;
		}
		
		while (currentIndex < length && letter(currentSymbol))
		{			
			if (!was[toLower(currentSymbol) - 'a'])
			{
				was[toLower(currentSymbol) - 'a'] = true;
				cout << currentSymbol;
			}
			
			currentIndex++;			
			currentSymbol = string[currentIndex];
		}
		clear(was, alphabetSize);
	}
}

int main()
{
	ifstream cin("input.txt");
	ofstream cout("output.txt");
	
	if (cin.fail())
	{
		cout << "There's no input file.";
		return 0;
	}
	
	const int maxSize = 1000;
	
	char string[maxSize] = {0};
	
	while (cin.getline(string, maxSize))
	{
		processString(string, strlen(string), cout);
		cout << '\n';
	}
	
	cin.close();
	cout.close();	
	return 0;
}