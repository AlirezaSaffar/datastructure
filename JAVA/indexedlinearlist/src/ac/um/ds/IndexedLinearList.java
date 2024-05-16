package ac.um.ds;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class IndexedLinearList<T> {
		 
		 
	
    public IndexedLinearList() {
        // Write your code here
    	mIbt = new IndexedBinaryTree();
    	 
    }

    public void insert(int idx, T data) throws IllegalAccessException {
        // Write your code here
    	int n =0;
    	int temp=0;
    	int check=0;
    	InternalIndexedBinaryTreeNode curr;
    	BinaryTreeNode x;
    	curr= mIbt.root; 
    	try {
    	if(idx>this.mIbt.size()) {
    		IllegalAccessException e= new IllegalAccessException();
			throw e;
    	}
    	}catch(IllegalAccessException e) {return;}
    	if(idx==this.mIbt.size()) {
    		if(idx==0) {this.mIbt.insertRootNode(data);return;}
    		while(curr.mRightChild!=null) curr=(InternalIndexedBinaryTreeNode) curr.mRightChild;
    		x= new BinaryTreeNode(curr);
			mIbt.insertRightChild(x, data);
			return;
    	}
    	while(check==0) {
    		temp=n;
    		n= n+curr.mLeftSize; 
    		if(n==idx) {
    			if(curr.mLeftChild==null) {
    				x= new BinaryTreeNode(curr);
    				this.mIbt.insertLeftChild(x, data);}else {
    					curr=(InternalIndexedBinaryTreeNode) curr.mLeftChild;
    					while(curr.mRightChild!=null) curr=(InternalIndexedBinaryTreeNode) curr.mRightChild;
    					x= new BinaryTreeNode(curr);
        				this.mIbt.insertRightChild(x, data);
    				}
    			check=1;
    		}
    		if(idx<n) {
    			curr=(InternalIndexedBinaryTreeNode) curr.mLeftChild;
    			n=temp;
    			continue;
    		}
    		if(idx>n) {
    			curr=(InternalIndexedBinaryTreeNode) curr.mRightChild;
    			
    			n++;
    		}
    	}
    }

    public T get(int idx) throws IllegalAccessException {
        // Write your code here
    	int n =0;
    	int temp=0;
    	InternalIndexedBinaryTreeNode curr;
    	curr= mIbt.root; 
    	while(true) {
    		temp=n;
    		n= n+curr.mLeftSize; 
    		if(n==idx) {
    			return (T) curr.mData;
    		}
    		if(idx<n) {
    			curr=(InternalIndexedBinaryTreeNode) curr.mLeftChild;
    			n=temp;
    			continue;
    			
    		}
    		if(idx>n) {
    			curr=(InternalIndexedBinaryTreeNode) curr.mRightChild;
    			n++;
    		}
    	}
    }

    public void remove(int idx) throws IllegalAccessException {
        // Write your code here
    	int n =0;
    	int temp=0;
    	InternalIndexedBinaryTreeNode curr;
    	InternalIndexedBinaryTreeNode curr2;
    	InternalIndexedBinaryTreeNode pre=this.mIbt.mInorderEnd;
    	curr= mIbt.root; 
    	try {
        	if(idx>=this.mIbt.size()) {
        		IllegalAccessException e= new IllegalAccessException();
    			throw e;
        	}
        	}catch(IllegalAccessException e) {return;}
    	while(true) {
    		temp=n;
    		n= n+curr.mLeftSize; 
    		if(n==idx) {
    			if(curr.mRightChild==null&&curr.mLeftChild==null) {
    			    pre=(InternalIndexedBinaryTreeNode) curr.mParent;
    				this.mIbt.deleteNode(pre,curr);
                      return;
    			}
    			if(curr.mRightChild!=null&&curr.mLeftChild!=null) {
    				curr2=(InternalIndexedBinaryTreeNode) curr.mLeftChild;
    				if(curr2.mRightChild!=null) {
    				while(curr2.mRightChild.mRightChild!=null)curr2=(InternalIndexedBinaryTreeNode) curr2.mRightChild;
    				curr.mData= curr2.mRightChild.mData;
    			    pre=(InternalIndexedBinaryTreeNode) curr2.mRightChild;
    				this.mIbt.deleteNode(curr2,pre);}else {
    			    pre=(InternalIndexedBinaryTreeNode) curr2.mParent;
        			this.mIbt.deleteNode(pre,curr2);
    				}
    				return;
    			}
    			pre=(InternalIndexedBinaryTreeNode) curr.mParent;
				this.mIbt.deleteNode(pre,curr);
                  return;
    		}
    		pre=curr;
    		if(idx<n) {
    			curr=(InternalIndexedBinaryTreeNode) curr.mLeftChild;
    			n=temp;
    		continue;	
    		}
    		if(idx>n) {
    			curr=(InternalIndexedBinaryTreeNode) curr.mRightChild;
    			n++;
    		}
    	}
    
    }

    public int size() {
        return mIbt.size();
      
    }

    public void drawTree() {
        System.out.println(mIbt.draw());
    }

    protected IndexedBinaryTree<T, InternalIndexedBinaryTreeNode<T>> mIbt;
}
