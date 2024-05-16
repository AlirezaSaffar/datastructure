#pragma once
#include "ISort.h"

template <class T>
class SelectionSort: public ISort<T>{
public:
	virtual void Sort(T* A, int n) {
     T temp;
        int j, k, id = 0;
        k = n;
        for (; k > 1;) {
            for (j = 1; j < k; j++) {
                if (A[j] > A[id]) {
                  id = j;
                }
            }
            temp = A[k - 1];
            A[k - 1] = A[id];
            A[id] = temp;
            k--;
            id = 0;
        }
    }

};


