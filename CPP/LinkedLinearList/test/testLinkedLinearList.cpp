#include <iostream>
#include "../src/LinkedLinearList.h"

using namespace std;

class X
{
public:
	void printStar(){
		cout << "* works" << endl;
	}

	void printArrow(){
		cout << "-> works" << endl;
	}
};

void testIteration();
void testDereferencing();
void testInsertRemove();

int main()
{	
	cout << "=============================================" << endl;
	cout << "Test 1. Dereferencing" << endl;
	cout << "=============================================" << endl;
	testDereferencing();
	cout << "\n";
	cout << "=============================================" << endl;
	cout << "Test 2. Insert/Remove" << endl;
	cout << "=============================================" << endl;
	testInsertRemove();
	cout << "\n\n";
	cout << "=============================================" << endl;
	cout << "Test 3. Iteration" << endl;
	cout << "=============================================" << endl;
	testIteration();


	int tmp;
	cin >> tmp;
	return 0;
}

void testDereferencing()
{
	LinkedLinearList<X> lll;
	LinkedLinearList<X>::Iterator itr;

	X x;
	itr = lll.begin();
	lll.insert(itr, x);

	itr = lll.begin();
	itr->printArrow();
	(*itr).printStar();
}


void testInsertRemove()
{
	LinkedLinearList<double> lll;
	LinkedLinearList<double>::Iterator itr;
	int N = 10;

	int expectedInsertRes[2][N+1] = {
		{9, 8, 7, 6, 5, 4, 3, 2, 1, 0},
		{9, 8, 10, 7, 6, 5, 4, 3, 2, 1, 0}};
	int expectedRemoveRes[7] = {8, 10, 6, 5, 3, 2, 0};

	itr = lll.begin();
	for (int i = 0; i < N; i++)
		itr = lll.insert(itr, i);

	cout<<"Insert output 1:\n";
	cout<< lll <<"\n";
	cout<<"EcpectedOutput:\n"
		<<"9, 8, 7, 6, 5, 4, 3, 2, 1, 0,\n\n";

	itr = lll.begin();
	for(int i = 0; i < N; i++){
		if(*(itr++) != expectedInsertRes[0][i]){
			cout << "\n";
			throw runtime_error("Incorrect insert function with post increment.");
		}
	}

	itr = lll.begin();
	itr++;
	itr++;
	itr = lll.insert(itr,10);

	cout<<"Insert output 2:\n";
	cout<< lll <<"\n";
	cout<<"EcpectedOutput:\n"
		<<"9, 8, 10, 7, 6, 5, 4, 3, 2, 1, 0,\n\n";

	itr = lll.begin();
	for(int i = 0; i < N+1; i++, itr++){
		if(*itr != expectedInsertRes[1][i]){
			cout << "\n";
			throw runtime_error("Incorrect insert function.");
		}
	}

	itr = lll.begin();
	for (int i = 0; i < N; i+=3){
		itr = lll.remove(itr);
		itr++;
		itr++;
	}

	cout<<"Remove output:\n";
	cout<<lll<<"\n";
	cout<<"EcpectedOutput:\n"
		<<"8, 10, 6, 5, 3, 2, 0,\n";

	itr = lll.begin();
	for(int i = 0; i < 7; i++, itr++){
		if(*itr != expectedRemoveRes[i]){
			cout << "\n";
			throw runtime_error("Incorrect remove function.");
		}
	}
}


void testIteration()
{
	LinkedLinearList<double> lll;
	LinkedLinearList<double>::Iterator itr;
	int N = 5;
	int expectedForwardRes[N] = {4, 3, 2, 1, 0};
	int expectedBackwardRes[N] = {0, 1, 2, 3, 4};

	itr = lll.begin();
	for (int i = 0 ; i < N; i++)
		itr = lll.insert(itr, i); 

	cout << "Expected Initial List:\n" 
		 << "4, 3, 2, 1, 0,\n\n";

	cout << "Expected Forward Iteration:\n" 
		 << "4, 3, 2, 1, 0,\n";

	cout << "Forward Iteration Output:\n" ;
	for (itr = lll.begin(); itr != lll.end(); itr++)
		cout << *itr << ", ";
	cout << "\n\n";

	itr = lll.begin();
	for(int i = 0; i < N; i++, itr++){
		if(*itr != expectedForwardRes[i]){
			cout << "\n";
			throw runtime_error("Incorrect forward iteration.");	
		}
	}

	cout << "Expected Backward Iteration:\n" 
		 << "0, 1, 2, 3, 4,\n";

	cout << "Backward Iteration Output:\n" ;
	for (itr = lll.rbegin(); itr != lll.rend(); itr--)
		cout << *itr << ", ";
	cout << "\n";

	itr = lll.rbegin();
	for(int i = 0; i < N; i++, itr--){
		if(*itr != expectedBackwardRes[i]){
			cout << "\n";
			throw runtime_error("Incorrect backward iteration.");	
		}
	}
}


