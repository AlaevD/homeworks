#pragma once

struct Set;

Set *createSet();
void destroy(Set *set);
void add(Set *set, int value);
void remove(Set *set, int value);
void printAscending(Set *set);
void printDescending(Set *set);
void printSpecial(Set *set);
bool isEmpty(Set *set);
bool exists(Set *set, int value);
