#include <iostream>
#include <cstring>
#include "huffman.h"
#include "priorityQueue.h"

using namespace std;

void calculateWeights(char *s, int n, int *weight)
{
	for (int i = 0; i < n; i++)
	{
		weight[s[i]]++;
	}
}

void printWeights(int *weight)
{
	const int maxSize = 10000;

	cout << "Symbol weights(unsorted):" << '\n';
	for (int i = 0; i < maxSize; i++)
	{
		if (weight[i])
		{
			cout << (char)i << " - " << weight[i] << '\n';
		}
	}
}

bool isLeaf(Node *node)
{
	return node->symbol != -1;
}

void getCodes(Node *node, char *s, char **code)
{
	if (isLeaf(node))
	{
		strcat(code[node->symbol], s);
		return;
	}
	
	if (node->left)
	{
		strcat(s, "0");
		getCodes(node->left, s, code);
		s[(int)strlen(s) - 1] = '\0';
	}
	if (node->right)
	{
		strcat(s, "1");
		getCodes(node->right, s, code);
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
	int n = strlen(s);
	for (int i = 1; i < n; i++)
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

char **declareCharMatrix(int size)
{
	char **result = new char*[size];
	for (int i = 0; i < size; i++)
	{
		result[i] = new char[size];
	}
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
		{
			result[i][j] = 0;
		}
	}
	return result;
}

void destroyCharMatrix(char **s, int size)
{
	for (int i = 0; i < size; i++)
	{
		delete[] s[i];
	}
	delete[] s;
}

void huffmanEncode(char *s, ofstream &fout)
{	
	const int maxSize = 10000;

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
	destroyQueue(q);
	
	char **code = declareCharMatrix(maxSize);
	char temp[maxSize] = {0};
	getCodes(root, temp, code);
	
	printTree(root, fout);
	fout << '\n';

	destroyNode(root);
	
	for (int i = 0; i < n; i++)
	{
		fout << code[s[i]];
	}
	destroyCharMatrix(code, maxSize);
}

Node *getNode(char *tree, int &i)
{
	int n = strlen(tree);
	while (i < n && (tree[i] == ' ' || tree[i] == ')'))
	{
		i++;
	}
	if (tree[i] == '(' && tree[i + 2] == '1')
	{
		Node *node = createNode(0, -1, nullptr, nullptr);
		i += 4;
		node->left = getNode(tree, i);
		node->right = getNode(tree, i);
		return node;
	}
	else if (tree[i] == '(')
	{
		Node *node = createNode(0, tree[i + 1], nullptr, nullptr);
		i += 3;
		node->left = getNode(tree, i);
		node->right = getNode(tree, i);
		return node;
	}
	else
	{
		if (tree[i + 4] == ')')
		{
			i += 6;
		}
		else
		{
			i += 5;
		}
		return nullptr;
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
	const int maxSize = 10000;

	char tree[maxSize];
	fin.getline(tree, maxSize);
	int i = 0;
	Node *root = getNode(tree, i);
	char code[maxSize] = { 0 };
	fin >> code;
	i = -1;
	int n = (int)strlen(code);
	while (i < n - 1)
	{
		getChar(root, i, code, fout);
	}

	destroyNode(root);
}
