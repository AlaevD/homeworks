#include <iostream>

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

void readArray(int *array, int arraySize)
{
	for (int i = 0; i < arraySize; i++)
	{
		cin >> array[i];
	}
}

void printArray(int *array, int arraySize)
{
	for (int i = 0; i < arraySize; i++)
	{
		cout << array[i] << ' ';
	}
	cout << '\n';
}

int main()
{
	int arraySize = 0;
	cout << "Enter size of array" << '\n';
	cin >> arraySize;
	
	int *a = new int[arraySize];
	
	cout << "Enter array elements" << '\n';
	readArray(a, arraySize);
	
	qSort(a, 0, arraySize - 1);
	
	cout << "Sorted array: ";
	printArray(a, arraySize);
	
	delete[] a;
	
	return 0;
}