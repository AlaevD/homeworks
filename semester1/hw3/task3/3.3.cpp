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

bool equal(char *s1, char *s2, int length)
{
	for (int i = 0; i < length; i++)
	{
		if (s1[i] != s2[i])
		{
			return false;
		}
	}
	return true;
}

void printPositive()
{
	cout << "The second string may be obtained by swapping characters in the first string." << '\n';
}

void printNegative()
{
	cout << "The second string cannot be obtained by swapping characters in the first string." << '\n';
}

int main()
{
	const int maxStringSize = 1000;
	
	char string1[maxStringSize] = {0};
	cout << "Enter the first string" << '\n';
	cin.getline(string1, maxStringSize);
	
	char string2[maxStringSize] = {0};
	cout << "Enter the second string" << '\n';
	cin.getline(string2, maxStringSize);
	
	int length = strlen(string1);
	
	if (length != strlen(string2))
	{
		printNegative();
		return 0;
	}
	
	qSort(string1, 0, length - 1);
	qSort(string2, 0, length - 1);
	
	equal(string1, string2, length) ? printPositive() : printNegative();
	
	return 0;
}