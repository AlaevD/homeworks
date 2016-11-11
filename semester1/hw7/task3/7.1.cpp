#include <iostream>
#include "set.h"

using namespace std;

void printMenu()
{
	cout << "Press" << '\n';
	cout << "      0 to exit" << '\n';
	cout << "      1 to add a new value" << '\n';
	cout << "      2 to remove a value" << '\n';
	cout << "      3 to check for existence" << '\n';
	cout << "      4 to print elements in ascending order" << '\n';
	cout << "      5 to print elements in descending order" << '\n';
	cout << "      6 to print elements in special order" << '\n';
}

int main()
{
	printMenu();
	
	enum {exit, addValue, removeValue, check, printAsc, printDesc, printSpec};
	
	int operation = 0;
	int value = 0;
	
	Set *set = createSet();
	
	while (cin >> operation)
	{
		if (operation == exit)
		{
			break;
		}
		if (operation == addValue)
		{
			cout << "Enter a value to add" << '\n';
			cin >> value;

			add(set, value);
		}
		else if (operation == removeValue)
		{
			cout << "Enter a value to remove" << '\n';
			cin >> value;

			remove(set, value);
		}
		else if (operation == check)
		{
			cout << "Enter a value to check" << '\n';
			cin >> value;

			cout << value << ' ';
			exists(set, value) ? cout << "does exist in set" : cout << "does not exist in set";
			cout << '\n';
		}
		else if (operation == printAsc)
		{
			printAscending(set);
			cout << '\n';
		}
		else if (operation == printDesc)
		{
			printDescending(set);
			cout << '\n';
		}
		else
		{
			printSpecial(set);
			cout << '\n';
		}
	}
	
	destroy(set);
	
	return 0;
}
