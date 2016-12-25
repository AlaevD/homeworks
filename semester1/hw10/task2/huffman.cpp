#include <iostream>
#include <cstring>
#include "huffman.h"
#include "priorityQueue.h"

using namespace std;

const int maxSize = 10000;

void calculateWeights(char *s, int n, int *weight)
{
	for (int i = 0; i < n; i++)
	{
		weight[s[i]]++;
	}
}

void printWeights(int *weight)
{
	cout << "Symbol weights(unsorted):" << '\n';
	for (int i = 0; i < maxSize; i++)
	{
		if (weight[i])
		{
			cout << (char)i << " - " << weight[i] << '\n';
		}
	}
}

char code[maxSize][maxSize] = {0};

bool isLeaf(Node *node)
{
	return node->symbol != -1;
}

void getCodes(Node *node, char *s)
{
	if (isLeaf(node))
	{
		strcat(code[node->symbol], s);
		return;
	}
	
	if (node->left)
	{
		strcat(s, "0");
		getCodes(node->left, s);
		s[(int)strlen(s) - 1] = '\0';
	}
	if (node->right)
	{
		strcat(s, "1");
		getCodes(node->right, s);
		s[(int)strlen(s) - 1] = '\0';
	}
}

void printTree(Node *node, ofstream &fout)
{
	if (node == nullptr)
	{
		fout << "null";
		return;
	}
	
	fout << "(";
	node->symbol == -1 ? fout << -1 << ' ' : fout << node->symbol << ' ';
	printTree(node->left, fout);
	fout << ' ';
	printTree(node->right, fout);
	fout << ')';
}

Node *createNode(int weight, char c, Node *left, Node *right)
{
	Node *newNode = new Node;
	newNode->left = left;
	newNode->right = right;
	newNode->weight = weight;
	newNode->symbol = c;
	return newNode;
}

bool containsOneChar(char *s)
{
	char c = s[0];
	for (int i = 1; i < (int)strlen(s); i++)
	{
		if (c != s[i])
		{
			return false;
		}
	}
	return true;
}

void processSingleCharCase(char *s, ofstream &fout)
{
	fout << "(-1 " << s[0] << ' ' << "null)" << '\n';
	int n = (int)strlen(s);
	for (int i = 0; i < n; i++)
	{
		fout << 0;
	}
}

void destroyNode(Node *node)
{
	if (!node)
	{
		return;
	}

	destroyNode(node->left);
	destroyNode(node->right);
	delete node;
}

void huffmanEncode(char *s, ofstream &fout)
{	
	int weight[maxSize] = {0};
	int n = (int)strlen(s);
	
	calculateWeights(s, n, weight);
	printWeights(weight);
	
	if (containsOneChar(s))
	{
		processSingleCharCase(s, fout);
		return;
	}
	
	PQueue *q = createPQueue();
	for (int i = 0; i < maxSize; i++) {
		if (weight[i])
		{
			queuePush(q, createNode(weight[i], (char)(i), nullptr, nullptr));
		}		
	}
	
	while (queueSize(q) > 1)
	{
		Node *a = queueTop(q);
		queuePop(q);
		Node *b = queueTop(q);
		queuePop(q);
		queuePush(q, createNode(a->weight + b->weight, -1, a, b));
	}
	
	Node *root = queueTop(q);
	queuePop(q);
	destroy(q);
	
	char temp[maxSize] = {0};
	getCodes(root, temp);
	
	printTree(root, fout);
	fout << '\n';

	destroyNode(root);
	
	for (int i = 0; i < n; i++)
	{
		fout << code[s[i]];
	}
}

Node *readNode(ifstream &fin)
{
	char string[maxSize];
	fin >> string;
	if (string[0] == '(' && string[1] == '-' && strlen(string) > 2)
	{
		Node *node = createNode(0, -1, nullptr, nullptr);
		node->left = readNode(fin);
		node->right = readNode(fin);
		return node;
	}
	else if (string[0] == '(')
	{
		Node *node = createNode(0, string[1], nullptr, nullptr);
		node->left = readNode(fin);
		node->right = readNode(fin);
		return node;
	}
	else
	{
		if ((int)strlen(string) == 1)
		{
			return createNode(0, string[0], nullptr, nullptr);
		}
		else
		{
			return nullptr;
		}
	}
}

void getChar(Node *node, int &i, char *code, ofstream &fout)
{
	if (isLeaf(node))
	{
		fout << node->symbol;
		return;
	}
	i++;
	code[i] == '1' ? getChar(node->right, i, code, fout) : getChar(node->left, i, code, fout);
}

void huffmanDecode(std::ifstream &fin, ofstream &fout)
{
	Node *root = readNode(fin);
	char code[maxSize] = { 0 };
	fin >> code;
	int i = -1;
	int n = (int)strlen(code);
	while (i < n - 1)
	{
		getChar(root, i, code, fout);
	}

	destroyNode(root);
}
