#pragma once
#include <iostream>

struct List;

List *createList();
void destroy(List *list);
void add(List *list, int value);
void remove(List *list, int value);
void print(List *list);
bool isEmpty(List *list);
