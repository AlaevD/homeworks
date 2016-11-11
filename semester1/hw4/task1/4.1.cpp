#include <iostream>

using namespace std;

int **declareArray(int arraySize)
{
	int **a = new int*[arraySize];
	for (int i = 0; i < arraySize; i++)
	{
		a[i] = new int[arraySize];
	}
	return a;
}

void readArray(int **a, int arraySize)
{
	cout << "Enter array elements" << '\n';
	for (int i = 0; i < arraySize; i++)
	{
		for (int j = 0; j < arraySize; j++)
		{
			cin >> a[i][j];
		}
	}
}

void printElements(int **a, int &currentRow, int &currentCol, int deltaRow, int deltaCol, int number)
{
	for (int i = 0; i < number; i++)
	{
		currentRow += deltaRow;
		currentCol += deltaCol;
		cout << a[currentRow][currentCol] << ' ';
	}
}

bool insideArray(int i, int j, int n)
{
	return 0 <= i && i < n && 0 <= j && j < n;
}

void printArrayBySpiral(int **a, int arraySize)
{
	cout << "Given array printed spirally starting from the middle: ";
	
	int middleIndex = arraySize / 2;
	cout << a[middleIndex][middleIndex] << ' ';
	
	int currentRow = middleIndex + 1;
	int currentCol = middleIndex;
	int number = 1;
	while (insideArray(currentRow, currentCol, arraySize))
	{
		cout << a[currentRow][currentCol] << ' ';
		
		printElements(a, currentRow, currentCol, 0, 1, number);
		number++;
		printElements(a, currentRow, currentCol, -1, 0, number);
		printElements(a, currentRow, currentCol, 0, -1, number);
		printElements(a, currentRow, currentCol, 1, 0, number);
		
		currentRow++;
		number++;
	}
}

void demolishArray(int **a, int arraySize)
{
	for (int i = 0; i < arraySize; i++)
	{
		delete[] a[i];
	}
	delete[] a;
}

int main()
{
	int n = 0;
	cout << "Enter odd n" << '\n';
	cin >> n;
	
	int **a = declareArray(n);
	readArray(a, n);
	
	printArrayBySpiral(a, n);
	
	demolishArray(a, n);
	
	return 0;
}