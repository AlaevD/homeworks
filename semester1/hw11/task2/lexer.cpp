#include <cstring>
#include "lexer.h"

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

void readDouble(char *s, int &i)
{
	int n = (int)strlen(s);
	char current = s[i];
	int state = 0;
	
	while (i < n)
	{
		if (move(state, current) == -1)
		{
			if (!(state == 2 || state == 4 || state == 7))
			{
				i = -1;
			}
			return;
		}
		
		state = move(state, current);
		
		i++;
		current = s[i];
	}
	
	if (state != 2 && state != 4 && state != 7)
	{
		i = -1;
	}
}
