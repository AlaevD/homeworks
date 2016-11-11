#include <iostream>
#include <fstream>
#include <cstring>
#include "tree.h"

using namespace std;

struct Node
{
	int value;
	char operation;
	Node *left;
	Node *right;	
};

struct Tree
{
	Node *root;
};

const int maxSize = 1000;
const int inf = (int)2e9;

Tree *createTree()
{
	Tree *newTree = new Tree;
	newTree->root = nullptr;
	return newTree;
}

Node *createNode(int value, char operation)
{
	Node *newNode = new Node;
	newNode->left = nullptr;
	newNode->right = nullptr;
	newNode->value = value;
	newNode->operation = operation;
	return newNode;
}

bool isNumber(char *string)
{
	return '0' <= string[0] && string[0] <= '9';
}

int charToDigit(char c)
{
	return c - '0';
}

int stringToInt(char *string)
{
	int result = 0;
	int length = (int)strlen(string);
	if (string[length - 1] == ')')
	{
		length--;
	}
	
	for (int i = 0; i < length; i++)
	{
		result *= 10;
		result += charToDigit(string[i]);
	}
	
	return result;
}

Node *readNode(ifstream &fin)
{
	char string[maxSize];
	fin >> string;
	if (string[0] == '(')
	{
		Node *node = createNode(inf, string[1]);
		node->left = readNode(fin);
		node->right = readNode(fin);
		return node;	
	}
	else
	{
		return createNode(stringToInt(string), '$');
	}
}

void readTree(Tree *tree, ifstream &fin)
{
	tree->root = readNode(fin);	
}

void printNode(Node *node)
{
	if (node->value != inf)
	{
		cout << node->value;
		return;
	}
	
	cout << '(';
	printNode(node->left);
	cout << ' ' << node->operation << ' ';
	printNode(node->right);
	cout << ')';
}

void printInfix(Tree *tree)
{
	printNode(tree->root);
}

void destroyNode(Node *&node)
{
	if (!node)
	{
		return;
	}
	
	destroyNode(node->left);
	destroyNode(node->right);
	delete node;
	node = nullptr;
}

void destroy(Tree *tree)
{
	destroyNode(tree->root);
	delete tree;
}

int operationResult(int a, int b, char operation)
{
	if (operation == '*')
	{
		return a * b;
	}
	
	if (operation == '+')
	{
		return a + b;
	}
	
	if (operation == '-')
	{
		return a - b;
	}
	
	if (operation == '/')
	{
		return a / b;
	}	
}

int calculateValue(Node *node)
{
	if (node->value != inf)
	{
		return node->value;
	}
	
	int left = calculateValue(node->left);
	int right = calculateValue(node->right);
	
	return operationResult(left, right, node->operation);
}

int calculateValue(Tree *tree)
{
	return calculateValue(tree->root);
}
