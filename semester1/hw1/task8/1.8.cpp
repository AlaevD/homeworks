#include <iostream>

using namespace std;

int factorialThroughRecursion(int n)
{
	if (n == 0 || n == 1)
	{
		return 1;
	}
	return n * factorialThroughRecursion(n - 1);
}

int factorialThroughCycle(int n)
{
	if (n == 0 || n == 1)
	{
		return 1;
	}
	
	int result = 1;
	for (int i = 2; i <= n; i++)
	{
		result *= i;
	}
	
	return result;
}

int main()
{
	int n = 0;
	cout << "Enter n" << '\n';
	cin >> n;
	
	cout << "n! calculated using recursion:" << '\n';
	cout << factorialThroughRecursion(n) << '\n';
	
	cout << "n! calculated using cycle:" << '\n';
	cout << factorialThroughCycle(n) << '\n';
	
	return 0;
}