#include <iostream>

using namespace std;

int power(int base, int exponent)
{
	if (exponent == 0)
	{
		return 1;
	}
	
	if (exponent == 1)
	{
		return base;
	}
	
	if (exponent % 2 == 0)
	{
		int squareRoot = power(base, exponent / 2);
		return squareRoot * squareRoot;
	}
	
	return base * power(base, exponent - 1);
}

int main()
{
	int base = 0;
	cout << "Enter a(base)" << '\n';
	cin >> base;
	
	int exponent = 0;
	cout << "Enter n(exponent)" << '\n';
	cin >> exponent;
	
	cout << "a^n = ";
	cout << power(base, exponent);
	
	return 0;
}