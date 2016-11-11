#include <fstream>
#pragma once

struct Tree;

Tree *createTree();
void readTree(Tree *tree, std::ifstream &fin);
void printInfix(Tree *tree);
void destroy(Tree *tree);
int calculateValue(Tree *tree);
