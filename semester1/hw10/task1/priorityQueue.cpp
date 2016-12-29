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
	return temp->data;
}

void destroy(Node *node)
{
	if (!node)
	{
		return;
	}

	destroy(node->left);
	destroy(node->right);
	delete node;
}

void removeFromHead(PQueue *q)
{
	q->head = q->head->next;
	q->size--;
	return;
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
	temp->next = temp->next->next;
	q->size--;
}

void destroyQueue(PQueue *q)
{
	if (!q->head)
	{
		return;
	}

	QNode *temp = q->head;
	while (temp->next)
	{
		QNode *toDelete = temp;
		temp = temp->next;
		delete toDelete;
	}
	delete temp;
	delete q;
}

int queueSize(PQueue *q)
{
	return q->size;
}
