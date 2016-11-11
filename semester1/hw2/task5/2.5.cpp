#include <iostream>

using namespace std;

void swap(int &a, int &b)
{
	int c = a;
	a = b;
	b = c;
}

int getLeftChild(int i)
{
	return 2 * i + 1;
}

int getRightChild(int i)
{
	return 2 * i + 2;
}

void heapify(int *array, int currentIndex, int size)
{
	int leftChild = getLeftChild(currentIndex);
	
	if (leftChild >= size)
	{
		return;
	}
	
	int rightChild = getRightChild(currentIndex);
	
	int maxChild = rightChild;
	if (leftChild == size - 1 || array[leftChild] > array[rightChild])
	{
		maxChild = leftChild;
	}
	
	if (array[currentIndex] < array[maxChild])
	{
		swap(array[currentIndex], array[maxChild]);
		heapify(array, maxChild, size);
	}	
}

void heapSort(int *array, int arraySize)
{
	for (int i = arraySize / 2 - 1; i > -1; i--)
	{
		heapify(array, i, arraySize);
	}
	
	for (int i = arraySize - 1; i > 0; i--)
	{
		swap(array[0], array[i]);
		heapify(array, 0, i);
	}
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
	
	heapSort(a, arraySize);
	
	cout << "Sorted array: ";
	printArray(a, arraySize);
	
	delete[] a;
	
	return 0;
}