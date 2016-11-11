#include <iostream>
#include <cmath>

using namespace std;

int main()
{
	int a = 0;
	int b = 0;
	
	cout << "Enter a(dividend)" << '\n';
	cin >> a;
	cout << "Enter b(divisor)" << '\n';
	cin >> b;
	
	if (b == 0) {
		cout << "Can't divide by zero";
		return 0;
	}
	
	int absA = abs(a);
	int absB = abs(b);
	int quotient = 0;
	while (absA >= absB)
	{
		absA -= absB;
		quotient++;
	}
	
	cout << "Integer division result: ";
	if (a > 0 && b > 0)
	{
		cout << quotient;
	}
	else if (a < 0 && b < 0)
	{
		cout << quotient + 1;
	}
	else
	{
		a > 0 ? cout << -quotient : cout << -quotient - 1;
	}
	
	return 0;
}