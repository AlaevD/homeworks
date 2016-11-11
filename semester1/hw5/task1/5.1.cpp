#include "List.h"

using namespace std;

void printMenu()
{
	cout << "Press" << '\n';
	cout << "      0 to exit" << '\n';
	cout << "      1 to add a new value" << '\n';
	cout << "      2 to remove an existing value" << '\n';
	cout << "      3 to print out the list" << '\n';
}

int main()
{
	List *list = createList();
	
	printMenu();
	
	enum {exit, addValue, removeValue, printList};
	int operation = 0;
	while (cin >> operation)
	{
		if (operation == exit)
		{
			break;
		}
		if (operation == addValue || operation == removeValue)
		{
			int value = 0;
			
			cout << "Enter a value to add/remove" << '\n';
			cin >> value;
			
			operation == addValue ? add(list, value) : remove(list, value);
		}
		else
		{
			print(list);
		}		
	}
	
	destroy(list);
	
	return 0;
}
