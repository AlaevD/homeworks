#include <iostream>
#include <fstream>

const int inf = (int)1e9;

using namespace std;

int **declareMatrix(int n, int m, int fill)
{
	int **a = new int*[n];
	for (int i = 0; i < n; i++)
	{
		a[i] = new int[m];
		for (int j = 0; j < m; j++)
		{
			a[i][j] = fill;
		}
	}
	return a;
}

void readMatrix(int **a, int n, int m, ifstream &fin)
{
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
		{
			fin >> a[i][j];
		}
	}
}

void destroyMatrix(int **a, int n)
{
	for (int i = 0; i < n; i++)
	{
		delete[] a[i];
	}
	delete[] a;
}

int h(int x1, int y1, int x2, int y2)
{
	return abs(x1 - x2) + abs(y1 - y2);
}

void findMin(int &x, int &y, int **heuristic, int **currents, int **used, int n, int m)
{
	int minValue = (int)1e9;
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
		{
			if (minValue > heuristic[i][j] && !used[i][j] && currents[i][j])
			{
				minValue = heuristic[i][j];
				x = i;
				y = j;
			}
		}
	}
}

bool inside(int x, int y, int n, int m)
{
	return 0 <= x && x < n && 0 <= y && y < m;
}

//parent[i][j] = x
//x = 3 - parent is on the left
//x = 1 - parent is on the right
//x = 2 - parent is below
//x = 4 - parent is above

void setParent(int **parent, int xParent, int yParent, int xChild, int yChild)
{
	if (xParent == xChild)
	{
		if (yChild > yParent)
		{
			parent[xChild][yChild] = 3;
		}
		else
		{
			parent[xChild][yChild] = 1;
		}
	}
	else
	{
		if (xChild > xParent)
		{
			parent[xChild][yChild] = 4;
		}
		else
		{
			parent[xChild][yChild] = 2;
		}
	}
}

//parent[i][j] = x
//x = 1 - parent is on the right
//x = 3 - parent is on the left
//x = 4 - parent is above
//x = 2 - parent is below 

int getParent(int **parent, int &x, int &y)
{
	int result = parent[x][y];
	if (result == 1)
	{
		y++;
	}
	else if (result == 3)
	{
		y--;
	}
	else if (result == 2)
	{
		x++;
	}
	else if (result == 4)
	{
		x--;
	}
	return result;
}

bool isEqual(int x1, int y1, int x2, int y2)
{
	return x1 == x2 && y1 == y2;
}

void aStar(int **a, int **used, int **distance, int **parent, int **heuristic, int **currents, int n, int m, int xStart, int yStart, int xFinish, int yFinish)
{
	distance[xStart][yStart] = 0;
	heuristic[xStart][yStart] = distance[xStart][yStart] + h(xStart, yStart, xFinish, yFinish);
	int xCurrent = -1;
	int yCurrent = 0;
	currents[xStart][yStart] = 1;
	for (int i = 0; i < n * m; i++)
	{
		findMin(xCurrent, yCurrent, heuristic, currents, used, n, m);
		
		if (isEqual(xCurrent, yCurrent, xFinish, yFinish))
		{
			used[xFinish][yFinish] = 1;
			break;
		}
		
		used[xCurrent][yCurrent] = 1;
		for (int dx = -1; dx <= 1; dx++)
		{
			for (int dy = -1; dy <= 1; dy++)
			{
				if (dx * dx + dy * dy == 1 && inside(xCurrent + dx, yCurrent + dy, n, m))
				{
					int xTo = xCurrent + dx;
					int yTo = yCurrent + dy;
					if (!used[xTo][yTo] && a[xTo][yTo] == 0 && distance[xCurrent][yCurrent] + 1 < distance[xTo][yTo])
					{
						setParent(parent, xCurrent, yCurrent, xTo, yTo);
						distance[xTo][yTo] = distance[xCurrent][yCurrent] + 1;
						heuristic[xTo][yTo] = distance[xTo][yTo] + h(xTo, yTo, xFinish, yFinish);
						currents[xTo][yTo] = 1;
					}
				}
			}
		}
	}
	
	if (!used[xFinish][yFinish])
	{
		cout << "No way";
		return;
	}
	
	a[xFinish][yFinish] = 10;
	while (getParent(parent, xFinish, yFinish) != -10)
	{
		a[xFinish][yFinish] = 10;
	}
	a[xFinish][yFinish] = 10;
	
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < m; j++)
		{
			if (a[i][j] == 10)
			{
				cout << '*';
			}
			else
			{
				cout << a[i][j];
			}
		}
		cout << '\n';
	}
}

int main()
{
	ifstream fin("input.txt");
	if (fin.fail())
	{
		cout << "No input file";
		return 0;
	}
	
	int n = 0;
	int m = 0;
	fin >> n >> m;
	
	int xStart = 0;
	int yStart = 0;
	cout << "Enter xStart([0..n - 1]) and yStart([0..m - 1])" << '\n';
	cin >> xStart >> yStart;
	
	int xFinish = 0;
	int yFinish = 0;
	cout << "Enter xFinish and yFinish" << '\n';
	cin >> xFinish >> yFinish;
	
	int **a = declareMatrix(n, m, 0);
	readMatrix(a, n, m, fin);
	
	int **used = declareMatrix(n, m, 0);
	
	int **distance = declareMatrix(n, m, inf);
	
	int **parent = declareMatrix(n, m, -10);
	
	int **heuristic = declareMatrix(n, m, inf);
	
	int **currents = declareMatrix(n, m, 0);
	
	aStar(a, used, distance, parent, heuristic, currents, n, m, xStart, yStart, xFinish, yFinish);	
	
	destroyMatrix(a, n);
	destroyMatrix(used, n);
	destroyMatrix(distance, n);
	destroyMatrix(parent, n);
	destroyMatrix(currents, n);
	destroyMatrix(heuristic, n);
	fin.close();
	return 0;
}
