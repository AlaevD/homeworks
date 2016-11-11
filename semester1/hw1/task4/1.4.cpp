#include <iostream>

using namespace std;

int main()
{
	int sumCount[28] = {0};
	for (int firstDigit = 0; firstDigit <= 9; firstDigit++)
	{
		for (int secondDigit = 0; secondDigit <= 9; secondDigit++)
		{
			for (int thirdDigit = 0; thirdDigit <= 9; thirdDigit++)
			{
				sumCount[firstDigit + secondDigit + thirdDigit]++;
			}
		}
	}
	
	int luckyTicketsNumber = 0;
	for (int i = 0; i < 28; i++)
	{
		luckyTicketsNumber += (sumCount[i] * sumCount[i]);
	}
	
	cout << "Number of lucky tickets: ";
	cout << luckyTicketsNumber;
	
	return 0;
}