#include <iostream>
#include <cstdlib>
#include <ctime>
#include <cstring>

using namespace std;

int charToDigit(char c)
{
	return c - '0';
}

bool equalDigits(char c, int digit)
{
	return digit == charToDigit(c);
}

int generateSecretNumber()
{
	int result = 0;
	bool reserved[10] = {false};
	
	int currentDigit = rand() % 9 + 1;
	result = currentDigit;
	reserved[currentDigit] = true;
	
	for (int i = 1; i <= 3; i++)
	{
		currentDigit = rand() % 10;
		
		while (reserved[currentDigit])
		{
			currentDigit = rand() % 10;
		}
		
		result *= 10;
		result += currentDigit;
		reserved[currentDigit] = true;
	}
	
	return result;
}

void initialize(int *secretNumberDigits, int secretNumber)
{
	for (int i = 3; i > -1; i--)
	{
		secretNumberDigits[i] = secretNumber % 10;
		secretNumber /= 10;
	}
}

bool endGame(char *guess, int *secretNumberDigits)
{
	for (int i = 3; i > -1; i--)
	{
		if (!(equalDigits(guess[i], secretNumberDigits[i])))
		{
			return false;
		}
	}
	return true;
}

void processAttempt(char *guess, int *secretNumberDigits)
{
	
	int bulls = 0;
	int cows = 0;
	for (int i = 0; i < 4; i++)
	{
		for (int j = 0; j < 4; j++)
		{
			if (equalDigits(guess[i], secretNumberDigits[j]))
			{
				i == j ? bulls++ : cows++;
			}
		}
	}
	
	cout << bulls << " Bulls, " << cows << " Cows" << '\n';
}

bool isDecimal(char c, bool withZero)
{
	bool result = '1' <= c && c <= '9';
	
	withZero ? result = result || c == '0' : result;
	
	return result;
}

bool valid(char *guess)
{
	if (strlen(guess) != 4)
	{
		return false;
	}
	
	bool ok = true;
	bool reserved[10] = {false};
	for (int i = 1; i < 4; i++)
	{
		if (!isDecimal(guess[i], true) || reserved[charToDigit(guess[i])])
		{
			ok = false;
		}
		reserved[charToDigit(guess[i])] = true;
	}
	
	return ok && isDecimal(guess[0], false) && !reserved[charToDigit(guess[0])];
}

const int maxSize = 1000;

void makeSureGuessIsValid(char *guess, int &totalAttempts)
{
	while (!valid(guess))
	{
		cout << "Invalid input. Try again" << '\n';
		cin.getline(guess, maxSize);
		totalAttempts++;
	}
}

int main()
{
	srand(time(0));	
		
	const int secretNumber = generateSecretNumber();
	
	int secretNumberDigits[4] = {0};
	initialize(secretNumberDigits, secretNumber);
	
	int totalAttempts = 1;
	char guess[maxSize] = {0};
	
	cout << "Guess a four-digit number (with all digits different)" << '\n';	
	cin.getline(guess, maxSize);
	
	makeSureGuessIsValid(guess, totalAttempts);	
	
	while (!endGame(guess, secretNumberDigits))
	{
		processAttempt(guess, secretNumberDigits);
		cin.getline(guess, maxSize);
		totalAttempts++;
		makeSureGuessIsValid(guess, totalAttempts);
	} 
	
	cout << "You've won!" << '\n' << "Total attempts: " << totalAttempts;
	
	return 0;
}