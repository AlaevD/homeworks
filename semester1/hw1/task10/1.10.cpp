#include <iostream>
#include <cstring>

using namespace std;

int main()
{
	const int maxSize = 1000;
	char str[maxSize] = {0};
	
	cout << "Enter a string" << '\n';
	cin >> str;
	
	int left = 0;
	int right = strlen(str) - 1;
	while (str[left] == str[right] && left < right)
	{
		left++;
		right--;
	}
	
	left >= right ? cout << "Palindrome" : cout << "Non-palindrome";
	
	return 0;
}