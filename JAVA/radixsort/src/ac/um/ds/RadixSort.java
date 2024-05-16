package ac.um.ds;

import java.lang.Math;

public class RadixSort<T extends IntegerKeyType> {

    public void sort(T[] array) {
        //Write your code here.
    	int n= array.length;
    	int r= this.calculateR(n);
    	int d=this.calculateD(array, r);
    	int i;
    	for(i=0;i<d;i++){
    		this.countingSort(array, (int)Math.pow(10,r )-1 , r, i+1);
    	}
    	
    	
    }
    private static int pow(int n){
		int i,ans;
		ans=1;
		for(i=0;i<n;i++){
			ans= ans*10;
		}
		return ans;
	}

    public int calculateR(int n){
        int r = (int) (Math.log10(n + 1));
        if (r > 4)
            r = 4;
        else if (r < 1)
            r = 1;
        return r;
    }

    public int calculateD(T[] array, int r){
        //Write your code here.
    	int n;
    	int i,max;
    	max=0;
    	n=0;
    	for(i=0;i<array.length;i++){
    		 n= array[i].getKey().getVal().length();
    		if(max<n){max=n;}
    		n=0;
    	}
    	int ans = max/r;
    	if(max%r!=0){ans++;}
    	return ans;
    }

    public void countingSort(T[] A, int k, int r, int j){
        //Write your code here.
    	int n=A.length;
    	UnboundedInteger x;
    	int i ;
    	int C[]= new int[k +1];
    	T[] B=(T[]) new IntegerKeyType[n];
    	int temp[]= new int[A.length];
    	
  for(i=0;i<A.length;i++){
	  temp[i]=A[i].getKey().getDigit(r, j);
  }
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

}
