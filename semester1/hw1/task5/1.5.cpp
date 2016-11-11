#include <iostream>
#include <cstring>

using namespace std;

int main() 
{
	const int maxSize = 1000;
	char string[maxSize] = {0};
	
	cout << "Enter a string" << '\n';
	cin >> string;
	
	int balance = 0;
	int stringLength = strlen(string);
	for (int i = 0; i < stringLength; i++)
	{
		if (string[i] == '(')
		{
			balance++;
		}
		else
		{
			balance--;
			if (balance < 0)
			{
				break;
			}
		}
	}
	balance == 0 ? cout << "Ok" : cout << "Bad";
		
	return 0;
}