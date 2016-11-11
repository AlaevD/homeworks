#include <iostream>
#include <cstring>

using namespace std;

void swap(char &a, char &b)
{
	char c = a;
	a = b;
	b = c;
}

int partition(char *a, int left, int right)
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

void qSort(char *a, int left, int right)
{
	if (left >= right)
	{
		return;
	}
	
	int pivot = partition(a, left, right);
	qSort(a, left, pivot - 1);
	qSort(a, pivot + 1, right);
}

void printZeros(int zeros)
{
	for (int i = 0; i < zeros; i++)
	{
		cout << 0;
	}
}

int main()
{
	const int maxStringSize = 1000;
	
	char number[maxStringSize] = {0};
	cout << "Enter a natural number" << '\n';
	cin.getline(number, maxStringSize);
	
	int length = strlen(number);
	
	qSort(number, 0, length - 1);
	
	int i = 0;
	int zeros = 0;
	while (number[i] == '0')
	{
		i++;
		zeros++;	
	}
	
	cout << "The lowest number which can be obtained by swapping digits in given n: ";
	cout << number[i];
	printZeros(zeros);
	while (i + 1 < length)
	{
		cout << number[i + 1];
		i++;
	}
	
	return 0;
}