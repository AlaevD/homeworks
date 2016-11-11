#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

void swap(int &a, int &b)
{
	int c = a;
	a = b;
	b = c;
}

int partition(int *a, int left, int right)
{
	int i = left;
	for (int j = left + 1; j <= right; j++)
	{
		if (a[j] < a[left])
		{
			swap(a[i + 1], a[j]);
			i++;
		}
	}
	swap(a[left], a[i]);
	return i;
}

void qSort(int *a, int left, int right)
{
	if (left >= right)
	{
		return;
	}
	
	int pivot = partition(a, left, right);
	qSort(a, left, pivot - 1);
	qSort(a, pivot + 1, right);
}

void printArray(int *a, int n)
{
	for (int i = 0; i < n; i++)
	{
		cout << a[i] << ' ';
	}
	cout << '\n';
}

int getLength(int n)
{
	if (n == 0)
	{
		return 1;
	}
	
	int result = 0;
	while (n > 0)
	{
		result++;
		n /= 10;
	}
	return result;
}

void printNumber(int n)
{
	int totalLength = 10;
	int currentLength = getLength(n);
	cout << n;
	for (int i = 0; i < totalLength - currentLength; i++)
	{
		cout << ' ';
	}
}

int getRandomNumber()
{
	int length = rand() % 11;
	
	if (length == 0)
	{
		return 0;
	}
	
	if (length == 10)
	{
		return 1000000000;
	}
	
	int result = 0;
	for (int i = 0; i < length; i++)
	{
		result *= 10;
		result += rand() % 10;
	}
	return result;
}

void initialize(int *a, int n)
{
	for (int i = 0; i < n; i++)
	{
		a[i] = getRandomNumber();
	}
}

bool isContained(int *a, int n, int needle)
{
	int left = 0;
	int right = n - 1;
	while (right - left > 1)
	{
		int middle = (right + left) / 2;
		if (a[middle] > needle)
		{
			right = middle;
		}
		else if (a[middle] == needle)
		{
			return true;
		}
		else
		{
			left = middle;
		}		
	}
	return a[left] == needle || a[right] == needle;
}

int main()
{
	srand(time(0));
	
	int n = 0;
	cout << "Enter n" << '\n';
	cin >> n;
	
	int k = 0;
	cout << "Enter k" << '\n';
	cin >> k;
	
	int *a = new int[n];
	initialize(a, n);
	
	qSort(a, 0, n - 1);
	
	int *needles = new int[k];
	initialize(needles, k);
	
	cout << "n-array:" << '\n';
	printArray(a, n);
	cout << '\n';
	
	cout << "k-array:" << '\n';
	printArray(needles, k);
	cout << '\n';
	
	cout << "+ means the number is contained in n-array" << '\n';
	cout << "- means the number is not contained in n-array" << '\n';
	
	for (int i = 0; i < k; i++)
	{
		printNumber(needles[i]);
		isContained(a, n, needles[i]) ? cout << " + \n" : cout << " - \n";
	}
	
	delete[] a;
	delete[] needles;
	
	return 0;
}