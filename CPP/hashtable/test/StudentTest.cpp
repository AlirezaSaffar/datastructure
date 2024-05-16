#include <iostream>
#include "..\src\HashTable.h"
#include "I.h"

using namespace std;

//For test1
int hashFuncTest1(int index){
	int map[2] = {0, 5};
	return index + map[index % 2];
}
int hashFunc2Test1(int index){
	return 1;
}

//For test2
int hashFuncTest2(int index){
	return index;
}
int hashFunc2Test2(int index){
	int k = 3;
	return k;
}

//For testGet
int hashFuncTestGet(int index){
	return index;
}
int hashFunc2TestGet(int index){
	return 1;
}

void testGet();
void testAssignRemove1();
void testAssignRemove2();
string convertToString(int res[], int n);

int main()
{
    cout << "=============================================" << endl;
	cout << "Test 1. Assign/Remove" << endl;
	cout << "=============================================" << endl;
    testAssignRemove1();
	cout << "\n";
	cout << "=============================================" << endl;
	cout << "Test 2. Assign/Remove" << endl;
	cout << "=============================================" << endl;
    testAssignRemove2();
	cout << "\n\n";
	cout << "=============================================" << endl;
	cout << "Test 3. Get (operator[])" << endl;
	cout << "=============================================" << endl;
    testGet();
}

void testAssignRemove1(){
	int test[11] = {6, 12, 34, 29, 28, 11, 23, 7, 0, 33, 30};
	int deleteItems[3] = {34, 12, 11};
	int capacity[11] = {7, 7, 7, 7, 17, 17, 17, 17, 17, 17, 17};
	int result[11][17] = {{-1, -1, -1, -1, -1, -1, 6},
						  {-1, -1, -1, -1, -1, 12, 6},
						  {34, -1, -1, -1, -1, 12, 6},
						  {34, 29, -1, -1, -1, 12, 6},
						  {34, 29, -1, -1, -1, -1, 6, -1, -1, -1, -1, 28, 12, -1, -1, -1, -1},
						  {34, 29, -1, -1, -1, -1, 6, -1, -1, -1, -1, 28, 12, -1, -1, -1, 11},
						  {34, 29, -1, -1, -1, -1, 6, -1, -1, -1, -1, 28, 12, 23, -1, -1, 11},
						  {34, 29, -1, -1, -1, -1, 6, -1, -1, -1, -1, 28, 12, 23, 7, -1, 11},
						  {34, 29, 0, -1, -1, -1, 6, -1, -1, -1, -1, 28, 12, 23, 7, -1, 11},
						  {34, 29, 0, -1, 33, -1, 6, -1, -1, -1, -1, 28, 12, 23, 7, -1, 11},
						  {34, 29, 0, -1, 33, -1, 6, -1, -1, -1, -1, 28, 12, 23, 7, 30, 11}};
	int deleteResult[3][17] = {
						  {29, 0, -1, -1, 33, -1, 6, -1, -1, -1, -1, 28, 12, 23, 7, 30, 11},
						  {29, 0, -1, -1, 33, -1, 6, -1, -1, -1, -1, 28, 23, 7, 30, -1, 11},
						  {29, 0, -1, -1, 33, -1, 6, -1, -1, -1, -1, 28, 23, 7, 30, -1, -1}};

	HashTable<int, int> *hashTable;
	hashTable = new HashTable<int, int>(hashFuncTest1, hashFunc2Test1, .7, 7);

	for (int i = 0; i < 11; i++)
	{
		cout << "Added: " << test[i] << endl;
		hashTable->assign(test[i], test[i]);

		cout << "Capacity is: " << hashTable->capacity() << endl;
		cout << "             " << capacity[i] << " <<Correct answer" << endl;

		if(capacity[i] != hashTable->capacity()){
			throw runtime_error("Incorrect capacity!");
		}

		hashTable->print();
		for (int j = 0; j < capacity[i]; j++)
		{
			if (result[i][j] != -1)
				cout << result[i][j] << "\t";
			else
				cout << "*"
					 << "\t";
		}
		cout << "<<Correct answer" << endl
			 << endl;

		string resString = convertToString(result[i], capacity[i]);
		if(resString != hashTable->toString()){
			throw runtime_error("Incorrect assign!");
		}
	}

	for (int i = 0; i < 3; i++)
	{
		cout << "Remove: " << deleteItems[i] << endl;
		hashTable->remove(deleteItems[i]);

		hashTable->print();
		for (int j = 0; j < 17; j++)
		{
			if (deleteResult[i][j] != -1)
				cout << deleteResult[i][j] << "\t";
			else
				cout << "*"
					 << "\t";
		}
		cout << "<<Correct answer" << endl
			 << endl;

		string resString = convertToString(deleteResult[i], 17);
		if(resString != hashTable->toString()){
			throw runtime_error("Incorrect remove!");
		}
	}
}

void testAssignRemove2(){
	int test[10] = {2, 12, 4, 11, 1, 32, 29, 21, 10, 30};
	int deleteItems[3] = {12, 60, 4};
	int capacity[10] = {7, 7, 7, 7, 17, 17, 17, 17, 17, 17};
	int result[10][17] = {{-1, -1, 2, -1, -1, -1, -1},
						  {-1, -1, 2, -1, -1, 12, -1},
						  {-1, -1, 2, -1, 4, 12, -1},
						  {11, -1, 2, -1, 4, 12, -1},
						  {-1, 1, 2, -1, 4, -1, -1, -1, -1, -1, -1, 11, 12, -1, -1, -1, -1},
						  {-1, 1, 2, -1, 4, -1, -1, -1, -1, -1, -1, 11, 12, -1, -1, 32, -1},
						  {-1, 1, 2, -1, 4, -1, -1, 29, -1, -1, -1, 11, 12, -1, -1, 32, -1},
						  {-1, 1, 2, -1, 4, -1, -1, 29, -1, -1, 21, 11, 12, -1, -1, 32, -1},
						  {-1, 1, 2, -1, 4, -1, -1, 29, -1, -1, 21, 11, 12, 10, -1, 32, -1},
						  {-1, 1, 2, -1, 4, -1, -1, 29, -1, -1, 21, 11, 12, 10, -1, 32, 30}};
	int deleteResult[3][17] = {
								{-1, 1, 2, -1, 4, -1, -1, 21, -1, -1, 10, 11, 29, 30, -1, 32, -1},
								{-1, 1, 2, -1, 4, -1, -1, 21, -1, -1, 10, 11, 29, 30, -1, 32, -1},
								{-1, 1, 2, -1, 21, -1, -1, -1, -1, -1, 10, 11, 29, 30, -1, 32, -1},
							   };

	HashTable<int, int> *hashTable;
	hashTable = new HashTable<int, int>(hashFuncTest2, hashFunc2Test2, .7, 7);

	for (int i = 0; i < 10; i++)
	{
		cout << "Added: " << test[i] << endl;
		hashTable->assign(test[i], test[i]);

		cout << "Capacity is: " << hashTable->capacity() << endl;
		cout << "             " << capacity[i] << " <<Correct answer" << endl;

		if(capacity[i] != hashTable->capacity()){
			throw runtime_error("Incorrect capacity!");
		}

		hashTable->print();
		for (int j = 0; j < capacity[i]; j++)
		{
			if (result[i][j] != -1)
				cout << result[i][j] << "\t";
			else
				cout << "*"
					 << "\t";
		}
		cout << "<<Correct answer" << endl
			 << endl;

		string resString = convertToString(result[i], capacity[i]);
		if(resString != hashTable->toString()){
			throw runtime_error("Incorrect assign!");
		}
	}

	for (int i = 0; i < 3; i++)
	{
		cout << "Remove: " << deleteItems[i] << endl;
		hashTable->remove(deleteItems[i]);

		hashTable->print();
		for (int j = 0; j < 17; j++)
		{
			if (deleteResult[i][j] != -1)
				cout << deleteResult[i][j] << "\t";
			else
				cout << "*"
					 << "\t";
		}
		cout << "<<Correct answer" << endl
			 << endl;

		string resString = convertToString(deleteResult[i], 17);
		if(resString != hashTable->toString()){
			throw runtime_error("Incorrect remove!");
		}
	}
}

void testGet(){

    int test[5] = {3, 14, 8, 6, 25};
	int capacity[5] = {5, 5, 11, 11, 11};
	int correctGet[3] = {103, 125, 108};

	HashTable<int, I> *hashTable = new HashTable<int, I>(hashFuncTestGet, hashFunc2TestGet, .5, 5);
	for (int i = 0; i < 5; i++)
	{
		cout << "Added: " << test[i] << endl;
		hashTable->assign(test[i], I(test[i]));

		cout << "Capacity is: " << hashTable->capacity() << endl;
		cout << "             " << capacity[i] << " <<Correct answer" << endl
			 << endl;
			 
		if(capacity[i] != hashTable->capacity())
			throw runtime_error("Incorrect capacity!");
	}

	cout << "Get key: 3" << endl;
	cout << (*hashTable)[3].getValue() << endl;
	cout << correctGet[0] << " <<Correct answer" << endl
		 << endl;

	if((*hashTable)[3].getValue() != correctGet[0])
		throw runtime_error("Incorrect get!");

	cout << "Get key: 25" << endl;
	cout << (*hashTable)[25].getValue() << endl;
	cout << correctGet[1] << " <<Correct answer" << endl
		 << endl;

	if((*hashTable)[25].getValue() != correctGet[1])
		throw runtime_error("Incorrect get!");

	cout << "Get key: 8" << endl;
	cout << (*hashTable)[8].getValue() << endl;
	cout << correctGet[2] << " <<Correct answer" << endl
		 << endl;

	if((*hashTable)[8].getValue() != correctGet[2])
		throw runtime_error("Incorrect get!");
}

string convertToString(int res[], int n){
    int size = 0;
    string s = "";
    for(int i = 0; i < n; i++){
        if(res[i] != -1)
            size++;
    }
    s += "size:" + to_string(size) + "\n";
    for(int i = 0; i < n-1; i++){
        if(res[i] != -1)
            s += to_string(res[i]) + "," + to_string(res[i]) + ", ";
        else
            s += "-1, ";
    }
    if(res[n-1] != -1)
        s += to_string(res[n-1]) + "," + to_string(res[n-1]);
    else
        s += "-1";

    return s;
}