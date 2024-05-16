package ac.um.ds;

public class CountingSort<T extends IntegerKeyType> {

    private short K;

    public CountingSort(short K) {
        this.K = K;
    }

    public void sort(T[] A) {
        //Write your code here
    	int n=A.length;
    	int C[]= new int[this.K +1];
    	T[] B=(T[]) new IntegerKeyType[n];
    	int i;
    	for(i=0;i<n;i++){
    		C[A[i].getKey()]++;
    	}
    	
         for(i=1;i<this.K +1;i++){
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

}



