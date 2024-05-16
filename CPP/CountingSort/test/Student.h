#pragma once

#include "../src/IntegerKeyType.h"
#include <string>
using namespace std;

class Student : public IntegerKeyType{
private:
    int age, studentNo;
    string name;

public:
    Student(){
        age = 0;
        name = "";
        studentNo = 0;
    }
    
    Student(int studentNo, string name, int age) {
        this->studentNo = studentNo;
        this->name = name;
        this->age = age;
    }
    
    int getKey(){
        return this->age;
    }

    string getName(){
        return this->name;
    }

    int getId(){
        return this->studentNo;
    }
    
    int compareTo(Student A) {
        if (age < A.getKey())
            return -1;
        else if (age > A.getKey())
            return 1;
        else
            return 0;
    }
};