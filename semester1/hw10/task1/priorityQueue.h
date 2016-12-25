#pragma once

struct Node
{
	char symbol;
	int weight;
	Node *left;
	Node *right;	
};

struct PQueue;

PQueue *createPQueue();
void queuePush(PQueue *q, Node *node);
void queuePop(PQueue *q);
void destroy(PQueue *q);
int queueSize(PQueue *q);
Node *queueTop(PQueue *q);
