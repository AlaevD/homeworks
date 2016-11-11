#include <iostream>
#include <cstring>

using namespace std;

int main()
{
	const int maxSize = 1000;
		
	char needle[maxSize] = {0};
	cout << "Enter a needle" << '\n';
	cin >> needle;
	
	char haystack[maxSize] = {0};
	cout << "Enter a haystack" << '\n';
	cin >> haystack;
	
	int numberOfOccurrences = 0;
	const int needleLength = strlen(needle);
	const int haystackLength = strlen(haystack);
	for (int i = 0; i <= haystackLength - needleLength; i++)
	{
		bool contains = true;
		for (int j = 0; j < strlen(needle); j++)
		{
			if (needle[j] != haystack[i + j])
			{
				contains = false;
				break;
			}
		}
		
		if (contains)
		{
			numberOfOccurrences++;
		}
	}
	
	cout << "Number of occurrences: ";
	cout << numberOfOccurrences;
	
	return 0;
}