#include <iostream>

using namespace std;

void reverse(int *a, int l, int r)
{
	while (l < r)
	{
		swap(a[l], a[r]);
		l++;
		r--;
	}
	return;
}

int main() 
{
	int n = 0;
	int m = 0;
	
	cout << "Enter m" << '\n';
	cin >> m;
	cout << "Enter n" << '\n';
	cin >> n;
	
	int *a = new int [m + n];
	
	cout << "Enter 1..m elements" << '\n';
	for (int i = 0; i < m; i++) 
	{
		cin >> a[i];
	}
	
	cout << "Enter m + 1..m + n elements" << '\n';
	for (int i = m; i < n + m; i++) 
	{
		cin >> a[i];
	}
	
	reverse(a, 0, m - 1);
	reverse(a, m, m + n - 1);
	reverse(a, 0, m + n - 1);
	
	cout << "New array:" << '\n';
	for (int i = 0; i < n + m; i++)
	{
		cout << a[i] << ' ';
	}
	delete[] a;	
	
	return 0;
}