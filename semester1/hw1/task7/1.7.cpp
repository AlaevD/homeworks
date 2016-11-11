#include <iostream>

using namespace std;

int main()
{
	int n = 0;
	cout << "Enter n" << '\n';
	cin >> n;
	
	bool isPrime[1 + n] = {0};
	for (int i = 2; i <= n; i++)
	{
		isPrime[i] = true;
	}
	
	cout << "Prime numbers less than or equal to n:" << '\n';
	for (int i = 2; i <= n; i++)
	{
		if (isPrime[i])
		{
			cout << i << ' ';
			for (int j = i + i; j <= n; j += i)
			{
				isPrime[j] = false;
			}
		}
	}
	
	return 0;
}