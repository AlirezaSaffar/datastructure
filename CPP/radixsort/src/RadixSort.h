#pragma once
#include <cmath>
#include <string>

using namespace std;

template <class T>
class RadixSort : public ISort<T>
{
public:
	virtual void sort(T array[], int n) {
		//Write your code here.
    	int r= this->calculateR(n);
    	int d=this->calculateD(array,n, r);
    	int i;
    	for(i=0;i<d;i++){
    		this->countingSort(array,n, (int)pow(10,r )-1 , r, i+1);
    	}

	}

	virtual int calculateR(int n) {
		int r = (int)log10(n + 1);
		if (r > 4)
			r = 4;
		else if (r < 1)
			r = 1;
		return r;
	}

	virtual int calculateD(T* array, int n, int r){
		//Write your code here.
		int m;

    	int i,max;
    	max=0;
    	m=0;

    	for(i=0;i<n;i++){

    		 m= array[i].getKey().getVal().length();
            if(max<m){max=m;}
    		m=0;
    	}
    	int ans = max/r;
    	if(max%r!=0){ans++;}
    	return ans;
	}


    virtual void countingSort(T *A, int n, int k, int r, int j){
		//Write your code here.
		int i ;
    	int *C= new int[k +1];
    	T *B=new T[n];
    	int *temp= new int[n];


    		
  for(i=0;i<n;i++){
	  temp[i]=A[i].getKey().getDigit(r, j);
  }

  for(i=0;i<k+1;i++){C[i]=0;}

    	for(i=0;i<n;i++){
            C[(temp[i])]++;
    	}

         for(i=1;i<k +1;i++){
        	 C[i]=C[i]+C[i-1];
         }
         for(i=n-1;i>-1;i--){

        	 B[C[( temp[i])]-1]=A[i];
        	 C[(temp[i] )]--;
         }
         for(i=0;i<n;i++){
        	 A[i]=B[i];
         }
    }

};

