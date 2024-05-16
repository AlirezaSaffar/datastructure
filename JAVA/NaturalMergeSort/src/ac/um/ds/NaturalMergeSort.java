package ac.um.ds;

import java.util.LinkedList;
import java.util.Queue;

public class NaturalMergeSort<T extends Comparable<T>> implements ISort<T> {
   public void sort(T[] arr, int n) {
	   //Write your code here
        Queue<Integer> points = new LinkedList();
       	findBorders(arr, n, points);
         int i,k,d,aa,b,c;
         k=0;
         int a[]= new int[n];
         d=0;
         while(true){
        	 a[d]= points.poll(); 
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
        		 merge(arr,a[aa],a[b],a[c]);
        	 }
        }
    }

    public void findBorders(T[] arr, int n, Queue<Integer> points) {
        //Write your code here
    	int i;
    	points.add(0);
    	for(i=0;i<n-1;i++){
    		 if(arr[i+1].compareTo(arr[i])<0){ 
    			 points.add(i+1);
    		}
    	}
    	points.add(n);
    }

    public void merge(T[] arr, int p, int q, int r) {
        //Write your code here
    	T temp[]=(T[]) new Comparable[r-p];
    	int i,j,h,k;
    	i=p;
    	j=q;
    	k=0;
    	while(i<q && j<r){
    		if(arr[i].compareTo(arr[j])<=0){
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
}























