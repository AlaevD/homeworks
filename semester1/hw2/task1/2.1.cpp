#include <iostream>

using namespace std;

int fibonacciThroughRecursion(int n)
{
	if (n == 1)
	{
		return 0;
	}
	
	if (n == 2 || n == 3)
	{
		return 1;
	}
	
	return fibonacciThroughRecursion(n - 1) + fibonacciThroughRecursion(n - 2);
}

int fibonacciThroughCycle(int n)
{
	if (n == 1)
	{
		return 0;
	}
	
	if (n == 2 || n == 3)
	{
		return 1;
	}
	
	int firstNumber = 0;
	int secondNumber = 1;
	int currentNumber = 1;
	
	for (int i = 4; i <= n; i++)
	{
		firstNumber = secondNumber;
		secondNumber = currentNumber;
		currentNumber = firstNumber + secondNumber;
	}
	
	return currentNumber;
}

int main()
{
	int n = 0;
	cout << "Enter n" << '\n';
	cin >> n;
	
	cout << "n-th Fibonacci number calculated using recursion:" << '\n';
	cout << fibonacciThroughRecursion(n) << '\n';
	
	cout << "n-th Fibonacci number calculated using cycle:" << '\n';
	cout << fibonacciThroughCycle(n);	
	
	return 0;
}