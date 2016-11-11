#include <iostream>

using namespace std;

void printFractions(int leftNumerator, int leftDenominator, int rightNumerator, int rightDenominator, int n)
{
	if (leftDenominator + rightDenominator > n)
	{
		return;
	}
	
	int midNumerator = leftNumerator + rightNumerator;
	int midDenominator = leftDenominator + rightDenominator;
	
	printFractions(leftNumerator, leftDenominator, midNumerator, midDenominator, n);
	
	cout << midNumerator << '/' << midDenominator << ' ';
	
	printFractions(midNumerator, midDenominator, rightNumerator, rightDenominator, n);
	
	return;
	
}

int main()
{
	int n = 0;
	cout << "Enter n(max denominator)" << '\n';
	cin >> n;
	
	cout << "All irreducible fractions between 0 and 1 with denominator not greater than n:" << '\n';
	printFractions(0, 1, 1, 1, n);
	
	return 0;
}