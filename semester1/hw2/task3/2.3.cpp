#include <iostream>

using namespace std;

void printAnswer(int *a, int actualSize)
{
	for (int i = 0; i <= actualSize; i++)
	{
		i < actualSize ? cout << a[i] << " + " : cout << a[i] << '\n';
	}
}

void decompose(int n, int currentSum, int lastSummand, int* answer, int lastSummandIndex)
{
	if (currentSum == n)
	{
		printAnswer(answer, lastSummandIndex);
		return;
	}
	
	for (int currentSummand = lastSummand; currentSummand < n; currentSummand++)
	{
		if (currentSum + currentSummand > n)
		{
			break;
		}
		
		answer[lastSummandIndex + 1] = currentSummand;
		decompose(n, currentSum + currentSummand, currentSummand, answer, lastSummandIndex + 1);
	}
}

int main()
{
	const int maxN = 10000;
	
	int n = 0;
	cout << "Enter n" << '\n';
	cin >> n;
	
	cout << "Representations of a given n as the sum:" << '\n';
	
	int answer[maxN] = {0};
	for (int firstSummand = 1; firstSummand <= n / 2; firstSummand++)
	{
		answer[0] = firstSummand;
		decompose(n, firstSummand, firstSummand, answer, 0);
	}
	
	return 0;
}