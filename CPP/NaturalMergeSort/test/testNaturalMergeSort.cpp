#include "../src/NaturalMergeSort.h"
#include <iostream>
#include <algorithm>
#include <cstring>
using namespace std;

class Temp
{
public:
	double value;

public:
	Temp()
	{
		this->value = (rand() % 10) * 1.32 * (rand() % 2 * (-1) + 0.5);
		if (this->value == -0)
			this->value = 0;
	}
	bool operator<(Temp const &t) { return this->value < t.value; }
	bool operator>(Temp const &t) { return this->value > t.value; }
	bool operator==(Temp const &t) { return this->value == t.value; }
	bool operator!=(Temp const &t) { return this->value != t.value; }
	bool operator>=(Temp const &t) { return this->value >= t.value; }
	bool operator<=(Temp const &t) { return this->value <= t.value; }
};

void check_template();
void check_sort();
void check_borders();
void check_merge();

int main()
{

	check_merge();
	cout << "\n\n==========================================\n\n";
	check_borders();
	cout << "\n\n==========================================\n\n";
	check_sort();
	cout << "\n\n==========================================\n\n";
	check_template();

	cout << "\n";
	int dummy;
	cin >> dummy;
}

void check_sort()
{
	int i, n = 10;
	ISort<int> *sorter;
	int *A;

	sorter = new NaturalMergeSort<int>();
	A = new int[n];

	for (i = 0; i < n; i++)
		A[i] = rand() % 100;

	cout << "Initial array of integers:\n";
	for (i = 0; i < n; i++)
	{
		cout << A[i] << " , ";
	}

	sorter->Sort(A, n);

	cout << "\n\nSorted array of integers:\n";
	for (i = 0; i < n; i++)
	{
		cout << A[i] << " , ";
	}

	if (!is_sorted(A, A + n))
	{
		cout << endl;
		throw runtime_error("Array is not sorted");
	}

	delete[] A;
	delete sorter;
}

void check_borders()
{
	int i, n = 15, correct_border_count = 7;
	NaturalMergeSort<int> *sorter;
	sorter = new NaturalMergeSort<int>();

	int *A, *B;
	A = new int[n]{2, 3, 3, 6, 4, 8, 9, 9, 2, 9, 8, 7, 1, 8, 9};
	B = new int[correct_border_count]{0, 4, 8, 10, 11, 12, 15};

	cout << "Initial array of integers:\n";
	for (int i = 0; i < n; i++)
	{
		cout << A[i] << " , ";
	}

	queue<int> borders = queue<int>();
	sorter->FindBorders(A, n, borders);

	cout << "\n\nCorrect border indexes:\n";
	for (int i = 0; i < correct_border_count; i++)
	{
		cout << B[i] << " , ";
	}

	int borders_count = borders.size();
	bool incorrect_borders = false;
	cout << "\n\nFound border indexes:\n";
	for (int i = 0; i < borders_count; i++)
	{
		if (borders.front() != B[i])
		{
			incorrect_borders = true;
		}
		cout << borders.front() << " , ";
		borders.pop();
	}
	if (incorrect_borders || borders_count != correct_border_count)
	{
		cout << endl;
		throw runtime_error("Incorrect Borders");
	}

	delete[] A;
	delete sorter;
}

void check_merge()
{
	int n = 13;
	NaturalMergeSort<int> *sorter;
	sorter = new NaturalMergeSort<int>();

	int *A, *correctResult;
	A = new int[n]{4, 1, 3, 2, 3, 3, 4, 5, 8, 1, 2, 3, 4};
	correctResult = new int[n]{4, 1, 3, 1, 2, 2, 3, 3, 3, 4, 4, 5, 8};

	cout << "Initial array of integers:\n";
	for (int i = 0; i < n; i++)
	{
		cout << A[i] << " , ";
	}

	cout << "\n\nCorrect merge results:\n";
	for (int i = 0; i < n; i++)
	{
		cout << correctResult[i] << " , ";
	}

	int r = n;
	int p = 3;
	int q = 9;

	sorter->Merge(A, p, q, r);

	cout << "\n\nMerged output:\n";
	for (int i = 0; i < n; i++)
	{
		cout << A[i] << " , ";
	}

	for (int i = 0; i < n; i++)
	{
		if (A[i] != correctResult[i])
		{
			cout << endl;
			throw runtime_error("Incorrect Merge");
		}
	}
}

void check_template()
{
	int i, n = 10;
	ISort<Temp> *sorter;
	Temp *A, *B;
	sorter = new NaturalMergeSort<Temp>();

	// The following arrays are initialized in Temp's constructor
	A = new Temp[n];
	B = new Temp[n];

	cout << "Initial array of class<Temp>:\n";
	for (i = 0; i < n; i++)
	{
		cout << A[i].value << " , ";
	}
	memcpy(B, A, n * sizeof(Temp));
	sorter->Sort(A, n);
	sort(B, B + n);

	cout << "\n\nSorted array of class<Temp>:\n";
	for (i = 0; i < n; i++)
	{
		cout << A[i].value << " , ";
	}

	for (i = 0; i < n; i++)
	{
		if (A[i].value != B[i].value)
		{
			cout << endl;
			throw runtime_error("You are not using template for variables!");
		}
	}
	delete[] A;
	delete sorter;
}
