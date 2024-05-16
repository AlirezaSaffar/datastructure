package ac.um.ds;

public class SelectionSort<T extends Comparable<T>> implements ISort<T> {
    @Override
    public void Sort(T[] A) {
        // Write your code here
    	T O=A[0];
    	T temp;
    	int j,k,id=0;
    	k=A.length;
    	for(;k>0;){
    		for(j=0;j<k;j++){
    			if(A[j].compareTo(O)==1){
    			O=A[j];
    				id=j;
    			}
    		}
    		temp=A[k-1];
    		A[k-1]=A[id];
    		A[id]=temp;
    		k--;
    		O=A[0];
    		id=0;
    	}
    }
}
