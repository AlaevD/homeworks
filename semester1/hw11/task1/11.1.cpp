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

int move(int &state, char c)
{
	switch (state)
	{
		case 0:
			if (sign(c))
			{
				return 1;
			}
			else if (digit(c))
			{
				return 2;
			}
			else
			{
				return -1;
			}
		case 1:
			if (digit(c))
			{
				return 2;
			}
			else
			{
				return -1;
			}
		case 2:
			if (digit(c))
			{
				return 2;
			}
			else if (c == '.')
			{
				return 3;
			}
			else if (c == 'E')
			{
				return 5;
			}
			else
			{
				return -1;
			}
		case 3:
			if (digit(c))
			{
				return 4;
			}
			else
			{
				return -1;
			}
		case 4:
			if (digit(c))
			{
				return 4;
			}
			else if (c == 'E')
			{
				return 5;
			}
			else
			{
				return -1;
			}
		case 5:
			if (sign(c))
			{
				return 6;
			}
			else if (digit(c))
			{
				return 7;
			}
			else
			{
				return -1;
			}
		case 6:
			if (digit(c))
			{
				return 7;
			}
			else
			{
				return -1;
			}
		case 7:
			if (digit(c))
			{
				return 7;
			}
			else
			{
				return -1;
			}
		default:
			return -1;
	}		
}

bool realNumber(char *s)
{
	int n = (int)strlen(s);
	
	int i = 0;
	char current = s[i];
	int state = 0;
	
	while (i < n)
	{
		state = move(state, current);
		if (state == -1)
		{
			break;
		}
		
		i++;
		current = s[i];
	}
	
	return state == 2 || state == 4 || state == 7;
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
