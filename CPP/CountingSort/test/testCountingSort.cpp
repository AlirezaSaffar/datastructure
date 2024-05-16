#include <iostream>
#include <time.h>
#include "../src/ISort.h"
#include "../src/CountingSort.h"
#include "Student.h"

using namespace std;

void printData(Student* data, int testSize);
Student* getData();
Student* getCorrectResult();

int main() {

    int testSize = 10;
    short k = 25;

    Student* data = new Student[testSize];
    Student* correctResult = new Student[testSize];
    CountingSort<Student> *sorter = new CountingSort<Student>(k);

    cout << "\n** Initialized array:" << endl;
    data = getData();
    printData(data, testSize);

    sorter->sort(data, testSize);

    cout << "\n\n** Sorted array:" << endl;
    printData(data, testSize);

    cout << "\n\n** Expected Results:" << endl;
    correctResult = getCorrectResult();
    printData(correctResult, testSize);

    for (int i = 0; i < testSize; i++) {
		if (data[i].getKey() != correctResult[i].getKey()){
            cout << endl;
            throw runtime_error("Incorrect Sort");
		}
    }
    
    for (int i = 0; i < testSize; i++) {

        if (data[i].getId() != correctResult[i].getId()){
            cout<<endl;
            throw runtime_error("Unstable Sort");
		}
    }

    cout << "\n\n";
    return 0;
}


Student* getData() {
    int testSize = 10;
    int ages[] = {20, 19, 19, 22, 20, 24, 19, 21, 24, 25};
    string names[] = {"John", "Jane", "Mike", "Emma", "Alex",
                        "Lisa", "Alex", "Alice", "David", "Sophia"};
    int studentId[] = {1001, 1002, 1003, 1004, 1005,
                            1006, 1007, 1008, 1009, 1010};

    Student* data = new Student[testSize];

    for (int i = 0; i < testSize; i++) {
        Student student = Student(studentId[i], names[i], ages[i]);
        data[i] = student;
    }

    return data;
}

Student* getCorrectResult() {
    int testSize = 10;
    int ages[] = {19, 19, 19, 20, 20, 21, 22, 24, 24, 25};
    string names[] = {"Jane", "Mike", "Alex", "John", "Alex",
                            "Alice", "Emma", "Lisa", "David", "Sophia"};
    int studentId[] = {1002, 1003, 1007, 1001, 1005,
                            1008, 1004, 1006, 1009, 1010};

    Student* expectedRes = new Student[testSize];
    
    for (int i = 0; i < testSize; i++) {
        Student student = Student(studentId[i], names[i], ages[i]);
        expectedRes[i] = student;
    }

    return expectedRes;
}

void printData(Student* data, int testSize){
    for (int i = 0; i < testSize; i++) {
        cout << "(" << data[i].getKey() << ", " << data[i].getName() <<
                    ", id:" << data[i].getId() << "), ";
    }
}
