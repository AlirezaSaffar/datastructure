#include <string>
#include <iostream>
#include "../src/IntegerKeyType.h"

using namespace std;

template <class T>
class Data : public IntegerKeyType<T>{
private:
    T key;
    int value;

public:

    Data() : key(T("0")), value(0) {}
    Data(T key, int value) : key(key), value(value) {}

    virtual ~Data(void) {}

    virtual int getValue() {
        return value;
    }

    virtual T getKey() override {
        return key;
    }
};