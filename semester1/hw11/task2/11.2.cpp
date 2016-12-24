#include <iostream>
#include <cstring>
#include "lexer.h"

using namespace std;

bool isOperation(char c)
{
	return c == '+' || c == '-' || c == '*' || c == '/';
}

bool T(char *str, int &i);

bool F(char *str, int &i)
{
	return (i == (int)strlen(str)) ? true : T(str, i);
}

bool T(char *str, int &i)
{
	char c = str[i];
	i++;
	if (isOperation(c))
	{
		readDouble(str, i);
		if (i == -1)
		{
			return false;
		}
		return F(str, i);
	}
	return false;
}

bool E(char *str, int &i)
{
	readDouble(str, i);
	if (i == -1)
	{
		return false;
	}
	
	return (i == (int)strlen(str)) ? true : T(str, i);
}

bool isCorrect(char *str)
{
	int i = 0;
	return E(str, i);
}

int main()
{
	const int maxSize = 10000;
	
	char str[maxSize] = {0};
	cin >> str;
	
	if (isCorrect(str))
	{
		cout << "Expression is correct" << '\n';
	}
	else
	{
		cout << "Expression is incorrect" << '\n';
	}
	
	return 0;
}
