#include "List.h"

using namespace std;

struct Node
{
	int value;
	Node *next;
};

struct List
{
	Node *head;
};

List *createList()
{
	List *newList = new List;
	newList->head = nullptr;
	return newList;
}

void destroy(List *list)
{
	while (list->head != nullptr)
	{
		Node *toDelete = list->head;
		list->head = list->head->next;
		delete toDelete;
	}
	delete list;
}

Node *lowerBound(List *list, int value)
{
	Node *temp = list->head;
	
	while (temp->next != nullptr && temp->next->value < value)
	{
		temp = temp->next;
	}	
	
	return temp;	
}

Node *createNode(int value, Node *next)
{
	Node *newNode = new Node;
	newNode->value = value;
	newNode->next = next;
	return newNode;
}

void addToEmpty(List *list, int value)
{
	list->head = createNode(value, nullptr);
}

void addToHead(List *list, int value)
{
	list->head = createNode(value, list->head);
}

void add(List *list, int value)
{
	if (isEmpty(list))
	{
		addToEmpty(list, value);
		return;
	}
	
	if (list->head->value > value)
	{
		addToHead(list, value);
		return;
	}
	
	Node *temp = lowerBound(list, value);
	
	temp->next = createNode(value, temp->next);
}

void removeNode(Node *previous)
{
	Node *toDelete = previous->next;
	previous->next = toDelete->next;
	delete toDelete;
}

void removeFromHead(List *list)
{
	Node *toDelete = list->head;
	list->head = toDelete->next;
	delete toDelete;
}

void remove(List *list, int value)
{
	if (isEmpty(list) || list->head->value > value)
	{
		return;
	}
	
	if (list->head->value == value)
	{
		removeFromHead(list);
		return;
	}
	
	Node *temp = lowerBound(list, value);
	
	if (temp == nullptr || temp->next->value > value)
	{
		return;
	}
	
	removeNode(temp);
}

void print(List *list)
{
	if (isEmpty(list))
	{
		cout << "List is empty" << '\n';
		return;
	}
	
	cout << "Current list elements: ";
	
	Node *temp = list->head;
	while (temp != nullptr)
	{
		cout << temp->value << ' ';
		temp = temp->next;
	}
	cout << '\n';
}

bool isEmpty(List *list)
{
	return list->head == nullptr;
}
