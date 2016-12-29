#include <iostream>
#include <cstring>

using namespace std;

bool digit(char c)
{
	return '0' <= c && c <= '9';
}

bool sign(char c)
{
	return c == '+' || c == '-';
}

enum states {fail = -1, start, bareSign, finish1, numberDot, finish2, numberE, numberESign, finish3};

int move(int &state, char c)
{
	switch (state)
	{
		case start:
			if (sign(c))
			{
				return bareSign;
			}
			else if (digit(c))
			{
				return finish1;
			}
			else
			{
				return fail;
			}
		case bareSign:
			if (digit(c))
			{
				return finish1;
			}
			else
			{
				return fail;
			}
		case finish1:
			if (digit(c))
			{
				return finish1;
			}
			else if (c == '.')
			{
				return numberDot;
			}
			else if (c == 'E')
			{
				return numberE;
			}
			else
			{
				return fail;
			}
		case numberDot:
			if (digit(c))
			{
				return finish2;
			}
			else
			{
				return fail;
			}
		case finish2:
			if (digit(c))
			{
				return finish2;
			}
			else if (c == 'E')
			{
				return numberE;
			}
			else
			{
				return fail;
			}
		case numberE:
			if (sign(c))
			{
				return numberESign;
			}
			else if (digit(c))
			{
				return finish3;
			}
			else
			{
				return fail;
			}
		case numberESign:
			if (digit(c))
			{
				return finish3;
			}
			else
			{
				return fail;
			}
		case finish3:
			if (digit(c))
			{
				return finish3;
			}
			else
			{
				return fail;
			}
		default:
			return fail;
	}		
}

bool finalState(int state)
{
	return state == finish1 || state == finish2 || state == finish3;
}

bool realNumber(char *s)
{
	int n = (int)strlen(s);
	
	int i = 0;
	char current = s[i];
	int state = start;
	
	while (i < n)
	{
		state = move(state, current);
		if (state == fail)
		{
			break;
		}
		
		i++;
		current = s[i];
	}
	
	return finalState(state);
}

int main()
{
	const int maxSize = 1000;
	
	char s[maxSize] = {0};
	
	cout << "Enter a string to check" << '\n';
	cin.getline(s, maxSize);
	
	if (realNumber(s))
	{
		cout << "Given string is a real number" << '\n';
	}
	else
	{
		cout << "Given string is not a real number" << '\n';
	}
	
	return 0;
}
