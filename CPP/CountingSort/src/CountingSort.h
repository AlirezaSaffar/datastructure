#pragma once
#include "ISort.h"

template <class T>
class CountingSort : public ISort<T>
{
private:
    short K;

public:
    CountingSort(short K) {
        this->K = K;
    }

    virtual void sort(T* A, int n) {
        // Write your code here
        int *C= new int[this->K+1];
         T *B = new T[n];
    	int i,j;
          for(i=0;i<this->K+1;i++){ C[i]=0;}
    	  for(i=0;i<n;i++){
    		C[A[i].getKey()]++;
    	}

         for(i=1;i<this->K +1;i++){
        	 C[i]=C[i]+C[i-1];
         }
         for(i=n-1;i>-1;i--){
        	 B[C[A[i].getKey()]-1]=A[i];
        	 C[A[i].getKey()]--;
         }
         for(i=0;i<n;i++){
        	 A[i]=B[i];
         }



    }
};
