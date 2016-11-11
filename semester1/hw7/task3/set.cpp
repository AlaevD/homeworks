#include <iostream>
#include "set.h"

using namespace std;

struct Node
{
	int value;
	int height;
	Node *left;
	Node *right;
};

struct Set
{
	Node *root;
};

int height(Node *node)
{
	return node ? node->height : 0;
}

int balanceFactor(Node *node)
{
	return height(node->right) - height(node->left);
}

void updateHeight(Node *node)
{
	int leftHeight = height(node->left);
	int rightHeight = height(node->right);
	
	node->height = (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
}

Node *rotateRight(Node *node)
{
	Node *pivot = node->left;
	node->left = pivot->right;
	pivot->right = node;
	
	updateHeight(node);
	updateHeight(pivot);
	
	return pivot;
}

Node *rotateLeft(Node *node)
{
	Node *pivot = node->right;
	node->right = pivot->left;
	pivot->left = node;
	
	updateHeight(node);
	updateHeight(pivot);
	
	return pivot;
}

Node *balance(Node *node)
{
	updateHeight(node);
	
	if (balanceFactor(node) == 2)
	{
		if (balanceFactor(node->right) < 0)
		{
			node->right = rotateRight(node->right);
		}
		return rotateLeft(node);
	}
	
	if (balanceFactor(node) == -2)
	{
		if (balanceFactor(node->left) > 0)
		{
			node->left = rotateLeft(node->left);
		}
		return rotateRight(node);
	}
	
	return node;
}

Set *createSet()
{
	Set *newSet = new Set;
	newSet->root = nullptr;
	return newSet;
}

bool isEmpty(Set *set)
{
	return set->root == nullptr;
}

void deleteNode(Node *&node)
{
	if (!node)
	{
		return;
	}
	
	deleteNode(node->left);
	deleteNode(node->right);
	delete node;
	node = nullptr;
}

void destroy(Set *set)
{
	deleteNode(set->root);
	delete set;
	set = nullptr;
}

bool exists(Set *set, int value)
{
	Node *temp = set->root;
	while (temp && value != temp->value)
	{
		if (value < temp->value)
		{
			temp = temp->left;
		}
		else
		{
			temp = temp->right;
		}
	}
	
	if (!temp)
	{
		return false;
	}
	return true;
}

Node *createNode(int value)
{
	Node *newNode = new Node;
	
	newNode->value = value;
	newNode->height = 1;
	
	newNode->left = nullptr;
	newNode->right = nullptr;
	
	return newNode;
}

Node *add(Node *node, int value)
{
	if (!node)
	{
		return createNode(value);
	}
	
	if (value < node->value)
	{
		node->left = add(node->left, value);
	}
	else
	{
		node->right = add(node->right, value);
	}
	return balance(node);
}

void add(Set *set, int value)
{
	if (exists(set, value))
	{
		return;
	}
	
	if (isEmpty(set))
	{
		set->root = createNode(value);
		return;
	}
	
	set->root = add(set->root, value);
}

bool hasNoChildren(Node *node)
{
	return !node->left && !node->right ;
}

bool hasOneChild(Node *node)
{
	return !node->left && node->right || node->left && !node->right;
}

Node *removeMax(Node *node)
{
	if (!node->right)
	{
		return node->left;
	}
	
	node->right = removeMax(node->right);
	return balance(node);
}

Node *remove(Node *&node, int value)
{
	if (!node)
	{
		return nullptr;
	}
	
	if (node->value < value)
	{
		node->right = remove(node->right, value);
	}
	else if (node->value > value)
	{
		node->left = remove(node->left, value);
	}
	else
	{
		Node *left = node->left;
		Node *right = node->right;
		
		delete node;
		
		if (!left)
		{
			return right;
		}
		
		Node *temp = left;
		while (temp->right)
		{
			temp = temp->right;
		}
		
		temp->left = removeMax(left);
		temp->right = right;
		
		return balance(temp);
	}
	
	return balance(node);
}

void remove(Set *set, int value)
{
	if (!exists(set, value))
	{
		return;
	}
	
	set->root = remove(set->root, value);
}

void printAscending(Node *node)
{
	if (!node)
	{
		return;
	}
	
	printAscending(node->left);
	cout << node->value << ' ';
	printAscending(node->right);
}

void printAscending(Set *set)
{
	if (isEmpty(set))
	{
		cout << "Set is empty";
		return;
	}
	printAscending(set->root);
}

void printDescending(Node *node)
{
	if (!node)
	{
		return;
	}
	
	printDescending(node->right);
	cout << node->value << ' ';
	printDescending(node->left);
}

void printDescending(Set *set)
{
	if (isEmpty(set))
	{
		cout << "Set is empty";
		return;
	}
	printDescending(set->root);
}

void printSpecial(Node *node)
{
	if (!node)
	{
		cout << "null";
		return;
	}
	
	cout << "(" << node->value << ' ';
	printSpecial(node->left);
	cout << ' ';
	printSpecial(node->right);
	cout << ')';
}

void printSpecial(Set *set)
{
	if (isEmpty(set))
	{
		cout << "Set is empty" << '\n';
		return;
	}
	printSpecial(set->root);
}
