#include <algorithm>
#include "priorityQueue.h"

using namespace std;

struct QNode
{
	Node *data;
	QNode *next;	
};

struct PQueue
{
	QNode *head;
	int size;
};

PQueue *createPQueue()
{
	PQueue *newQueue = new PQueue;
	newQueue->head = nullptr;
	newQueue->size = 0;
	return newQueue;
}

QNode *createQNode(Node *data, QNode *next)
{
	QNode *newNode = new QNode;
	newNode->data = data;
	newNode->next = next;
	return newNode;
}

void queuePush(PQueue *q, Node *node)
{
	if (!q->head)
	{
		q->head = createQNode(node, nullptr);
		q->size = 1;
		return;
	}
	
	QNode *temp = createQNode(node, q->head);
	q->head = temp;
	q->size++;
}

int findMin(PQueue *q)
{
	int result = (int)1e9;
	QNode *temp = q->head;
	while (temp)
	{
		result = min(result, temp->data->weight);
		temp = temp->next;
	}
	return result;
}

Node *queueTop(PQueue *q)
{
	int minWeight = findMin(q);
	
	QNode *temp = q->head;
	while (temp->data->weight != minWeight)
	{
		temp = temp->next;
	}
	
	Node *result = new Node;
	result->left = temp->data->left;
	result->right = temp->data->right;
	result->symbol = temp->data->symbol;
	result->weight = minWeight;
	
	return result;
}

void removeFromHead(PQueue *q)
{
	QNode *toDelete = q->head;
	q->head = toDelete->next;
	delete toDelete->data;
	delete toDelete;
	q->size--;
}

void queuePop(PQueue *q)
{
	int minWeight = findMin(q);
	
	if (q->head->data->weight == minWeight)
	{
		removeFromHead(q);
		return;
	}
	
	QNode *temp = q->head;
	while (temp->next->data->weight != minWeight)
	{
		temp = temp->next;
	}
	
	QNode *toDelete = temp->next;
	temp->next = toDelete->next;
	delete toDelete->data;
	delete toDelete;
	q->size--;
}

void destroy(PQueue *q)
{
	if (!q)
	{
		return;
	}
	
	if (!q->head)
	{
		delete q;
		return;
	}

	QNode *temp = q->head;
	while (temp->next)
	{
		QNode *toDelete = temp;
		temp = temp->next;
		delete toDelete->data;
		delete toDelete;
	}
	delete temp->data;
	delete temp;
	delete q;
}

int queueSize(PQueue *q)
{
	return q->size;
}
