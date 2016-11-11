#include <iostream>

using namespace std;

int main()
{
	int x = 0;	
	cout << "Enter X" << '\n';
	cin >> x;
	
	int sqrX = x * x;
	
	cout << "Expression value: ";
	cout << (sqrX + 1) * (sqrX + x) + 1;
	
	return 0;
}