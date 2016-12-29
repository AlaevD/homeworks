#include <fstream>
#pragma once

void huffmanEncode(char *s, std::ofstream &fout);
void huffmanDecode(std::ifstream &fin, std::ofstream &fout);
