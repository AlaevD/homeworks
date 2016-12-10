#include <iostream>
#include <fstream>

using namespace std;

int **declareMatrix(int rows, int columns)
{
	int **a = new int*[rows];
	for (int i = 0; i < rows; i++)
	{
		a[i] = new int[columns];
		for (int j = 0; j < columns; j++)
		{
			a[i][j] = -1;
		}
	}
	return a;
}

int *declareIntArray(int size, int fill)
{
	int *a = new int[size];
	for (int i = 0; i < size; i++)
	{
		a[i] = fill;
	}
	return a;
}

bool *declareBoolArray(int size)
{
	bool *a = new bool[size];
	for (int i = 0; i < size; i++)
	{
		a[i] = false;
	}
	return a;
}

void unravelPath(int *parent, int i)
{
	if (i == 0)
	{
		cout << 1 << ' ';
		return;
	}
	unravelPath(parent, parent[i]);
	cout << i + 1 << ' ';
}

void destroyMatrix(int **a, int rows)
{
	for (int i = 0; i < rows; i++)
	{
		delete[] a[i];
	}
	delete[] a;
}

void destroyIntArray(int *a)
{
	delete[] a;
}

void destroyBoolArray(bool *a)
{
	delete[] a;
}

const int inf = (int)1e9;

void dijkstra(int **adjM, int *distance, bool *used, int *parent, int n, int m)
{
	distance[0] = 0;
	int current = 0;
	int dest = 0;
	int length = 0;
	cout << "Conquering path: ";
	for (int i = 0; i < n; i++)
	{
		current = -1;
		for (int j = 0; j < n; j++)
		{
			if (!used[j] && (current == -1 || distance[j] < distance[current]))
			{
				current = j;
			}
		}
		
		cout << current + 1 << ' ';
		used[current] = true;
		for (int j = 0; j < n; j++)
		{
			if (adjM[i][j] != -1)
			{
				dest = j;
				length = adjM[i][j];
				if (distance[current] + length < distance[dest])
				{
					distance[dest] = distance[current] + length;
					parent[dest] = current;
				}
			}
		}
	}
	cout << '\n';
}

int main()
{
	ifstream fin("input.txt");
	
	if (fin.fail())
	{
		cout << "No input file" << '\n';
		return 0;
	}
	
	int n = 0;
	int m = 0;
	fin >> n >> m;
	
	int **adjM = declareMatrix(n, m);
		
	int a = 0;
	int b = 0;
	int len = 0;
	
	for (int i = 0; i < m; i++)
	{
		fin >> a >> b >> len;
		adjM[a - 1][b - 1] = len;
		adjM[b - 1][a - 1] = len;
	}
	
	int *distance = declareIntArray(n, inf);
	int *parent = declareIntArray(n, -1);
	bool *used = declareBoolArray(n);
	
	dijkstra(adjM, distance, used, parent, n, m);
	
	cout << '\n';
	cout << "Distance between first and i-th town:" << '\n';
	for (int i = 0; i < n; i++)
	{
		cout << "i = " << i + 1 << ", distance = " << distance[i] << '\n';
	}
	cout << '\n';
	
	cout << "Path from first to i-th town" << '\n';
	for (int i = 0; i < n; i++)
	{
		cout << "i = " << i + 1 << ", path: ";
		unravelPath(parent, i);
		cout << '\n';
	}
	
	destroyMatrix(adjM, n);
	destroyIntArray(distance);
	destroyIntArray(parent);
	destroyBoolArray(used);
	fin.close();
	
	return 0;
}
