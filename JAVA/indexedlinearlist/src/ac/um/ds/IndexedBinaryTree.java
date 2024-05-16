package ac.um.ds;

@SuppressWarnings("unchecked")
public class IndexedBinaryTree<T, IBTN extends InternalIndexedBinaryTreeNode<T>> extends BinaryTree<T, IBTN> {
	private IBTN curr;
    public IndexedBinaryTree() {
        // Write your code here
    	super();
        this.dummy1.mLeftSize=1;
    	this.mInorderEnd.mLeftSize=1;
    	this.dummy3.mLeftSize=0;
    	this.dummy4.mLeftSize=0;
    }

    protected IBTN createInternalBinaryTreeNodeInstance() {
        return (IBTN) new InternalIndexedBinaryTreeNode<T>();
    }

    @Override
    public void insertRootNode(T data) throws IllegalAccessException {
        // Write your code here
    	try {
    		super.insertRootNode(data);
    	}catch(IllegalAccessException e  ){}
    	
    }

    @Override
    public void insertLeftChild(BinaryTreeNode<T, IBTN> parentNode, T data) throws IllegalAccessException {
        // Write your code here
    	try {
    		if(parentNode.mActualNode.mLeftChild!=null) {
    			IllegalAccessException e= new IllegalAccessException();
    			throw e;
    		}
    	super.insertLeftChild(parentNode, data);
    	this.curr=parentNode.mActualNode;
    	InternalIndexedBinaryTreeNode temp;
    	parentNode.mActualNode.mLeftSize++;
    	while(curr!=this.mInorderEnd) {
    			temp=curr;
    		    curr= (IBTN) curr.getParent();
    			if(curr.mLeftChild==temp) {curr.mLeftSize++;} 
    		}   
    		
    	}catch(IllegalAccessException e){}
    	
    	
    }

    @Override
    public void insertRightChild(BinaryTreeNode<T, IBTN> parentNode, T data) throws IllegalAccessException {
        // Write your code here
    	try {
    		if(parentNode.mActualNode.mRightChild!=null) {
    			IllegalAccessException e= new IllegalAccessException();
    			throw e;
    		}
        super.insertRightChild(parentNode, data);
    	this.curr=parentNode.mActualNode;
    	InternalIndexedBinaryTreeNode temp= new InternalIndexedBinaryTreeNode();
    	while(curr!=this.mInorderEnd) {
    			 temp=curr;
    	         curr=(IBTN) curr.getParent();
    			 if(curr.mLeftChild==temp) {curr.mLeftSize++;} 
    		}   
    		
    	}catch(IllegalAccessException e){}
    }

    protected void deleteNode(BinaryTreeNode<T, IBTN> node) throws IllegalAccessException {
        // Write your code here
    	IBTN theNode = node.mActualNode;
    	IBTN parentNode= (IBTN) theNode.mParent;
    	try {
    		if(theNode.mRightChild!=null&&theNode.mLeftChild!=null) {
        		IllegalAccessException e= new IllegalAccessException();
    			throw e;
        	}
    	this.isleftchild=false;
    	super.deleteNode(parentNode, theNode);
    	curr= node.mActualNode;
		IBTN temp2;
		if(this.isleftchild) parentNode.mLeftSize--;
	 	while(curr.mParent!=null) {
			temp2=curr;
            curr=(IBTN) curr.getParent();
			if(curr.mLeftChild==temp2) {curr.mLeftSize--;} 
		}  
    	}catch(IllegalAccessException e) {}
    }

    @Override
    protected void deleteNode(IBTN parentNode, IBTN theNode) throws IllegalAccessException {
        // Write your code here
    	try {
    		if(theNode.mRightChild!=null&&theNode.mLeftChild!=null) {
        		IllegalAccessException e= new IllegalAccessException();
    			throw e;
        	}
    		this.isleftchild=false;
    		super.deleteNode(parentNode, theNode);
    	    curr = parentNode;
    		IBTN temp2;
    		if(this.isleftchild) parentNode.mLeftSize--;
    	 	while(curr!=this.mInorderEnd) {
    			temp2=curr;
    		    curr=(IBTN) curr.getParent();
    			if(curr.mLeftChild==temp2) {curr.mLeftSize--;} 
    		}  
    	}catch(IllegalAccessException e){}
 
    }

    public int getLeftSize(BinaryTreeNode<T, IBTN> node) {
        return getActualNode(node).getLeftSize();
    }
}
