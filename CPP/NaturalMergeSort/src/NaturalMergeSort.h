#pragma once
#include "ISort.h"
#include <queue>
using namespace std;


template <class T>
class NaturalMergeSort : public ISort<T>
{
public:
	virtual void Sort(T *arr, int n)
	{
		//Write your code here
		queue<int> points = queue<int>();
		FindBorders(arr, n, points);
  int i,k,co,d,aa,b,c;
         k=0;
         int *a= new int[n];
         d=0;

         while(true){
              a[d]= points.front();
        	  points.pop();

        	 d++;
        	 if(a[d-1]==n){break;}

         }
         for(k=1;k<n;k=k*2){
        	 for(i=0;i<d;i=i+k*2){
        		 aa=i;
        		 b=i+k;
        		 c=i+2*k;
        		   if(b>d-1){b=d-1;}
        		 if(c>d-1){c=d-1;}
        		 Merge(arr,a[aa],a[b],a[c]);
        	 }


         }
		
	}



	virtual void FindBorders(T *arr, int n, queue<int> &points)
	{
		//Write your code here
		int i;
    	points.push(0);
    	for(i=0;i<n-1;i++){
    		 if(arr[i+1]<arr[i]){
    			 points.push(i+1);

    		 }

    	}
    	points.push(n);
	}

	virtual void Merge(T *arr, int p, int q, int r)
	{
		//Write your code here
	
		T *temp= new T[r-p];
    	int i,j,h,k;
    	i=p;
    	j=q;
    	k=0;
    	while(i<q && j<r){
    		if(arr[i]<=arr[j]){
    			temp[k]=arr[i];i++;
    		}else{
    			temp[k]=arr[j];j++;
    		}
    		k++;

    	}
    	while(i<q){
			temp[k]=arr[i];i++;k++;
		}
		while(j<r){
			temp[k]=arr[j];j++;k++;
		}
		for(h=0;h<r-p;h++){
			arr[p+h]=temp[h];
		}
	}
};
