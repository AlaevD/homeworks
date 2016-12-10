#include <cstring>
#include <iostream>

using namespace std;

const int mod = (int)1e9 + 7;
const int base = 101;

int getHash(char *s, int start, int length)
{
	long long result = 0;
	long long multiplier = 1;
	for (int i = start + length - 1; i >= start; i--)
	{
		result += multiplier * (int)s[i];
		result %= mod;
		
		multiplier *= base;
		multiplier %= mod;
	}
	return result;
}

void checkForOccurence(char *s, char *needle, int length, int start, bool &occurs)
{
	for (int i = start; i < length; i++)
	{
		if (needle[i] != s[i])
		{
			return;
		}
	}
	occurs = true;
	cout << start << ' ';
}

int power(int base, int exp)
{
	if (exp == 0)
	{
		return 1;
	}
	if (exp == 1)
	{
		return base;
	}
	
	if (exp % 2 == 0)
	{
		int sqrRoot = power(base, exp / 2);
		return (sqrRoot * sqrRoot) % mod;
	}
	else
	{
		return (base * power(base, exp - 1)) % mod;
	}
}

int updateHash(int hash, char *s, int index, int length)
{
	long long result = base * (hash - (int)s[index] * power(base, length - 1));
	result += s[index + length];
	result %= mod;
	return result;
}

void rabinKarp(char *s, char *needle, bool &occurs)
{
	int n = (int)strlen(s);
	int m = (int)strlen(needle);
	
	int sHash = getHash(s, 0, m);
	int needleHash = getHash(needle, 0, m);
	cout << "Occurences: ";
	for (int i = 0; i < n - m + 1; i++)
	{
		if (sHash == needleHash)
		{
			checkForOccurence(s, needle, m, i, occurs);
		}
		sHash = updateHash(sHash, s, i, m);
	}
}

int main()
{
	const int maxSize = 1000;
	
	char haystack[maxSize] = {0};
	cout << "Enter a haystack" << '\n';
	cin >> haystack;
	
	char needle[maxSize] = {0};
	cout << "Enter a needle" << '\n';
	cin >> needle;
	
	bool occurs = false;
	rabinKarp(haystack, needle, occurs);
	
	if (!occurs)
	{
		cout << "did not find any" << '\n';
	}	
	
	return 0;
}
