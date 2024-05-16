package ac.um.ds;

@SuppressWarnings("unchecked")
public class InorderPreservingBinaryTree<T, IBTN extends InternalInorderPreservingBinaryTreeNode<T>>
        extends BinaryTree<T, IBTN> {
	
	
	protected boolean rotateposition;
	
    public InorderPreservingBinaryTree() {
        super();
    }

    @Override
    protected IBTN createInternalBinaryTreeNodeInstance() {
        return (IBTN) new InternalInorderPreservingBinaryTreeNode<T>();
    }

    protected void RR(IBTN A) {
        // Write your code here
    	IBTN parent,br,b;
    	parent = (IBTN) A.mParent;
    	b=(IBTN) A.mRightChild;
    	br=(IBTN) b.mLeftChild;
    	if(parent.mLeftChild==A) {
    		parent.mLeftChild=b;
    		b.mParent=parent;
    	}else{parent.mRightChild=b;
    	b.mParent=parent;}
    	b.mLeftChild=A;
    	A.mParent=b;
    	A.mRightChild=br;
    	if(br!=null)
    	br.mParent=A;
    
        updateAfterRR(A);
       
      
    }

    protected void updateAfterRR(IBTN node) { 
    }

    protected void LL(IBTN A) {
        // Write your code here
    	IBTN parent,br,b;
    	parent = (IBTN) A.mParent;
    	b=(IBTN) A.mLeftChild;
    	br=(IBTN) b.mRightChild;
    	if(parent.mLeftChild==A) {
    		parent.mLeftChild=b;
    		b.mParent=parent;
    	}else{parent.mRightChild=b;
    	b.mParent=parent;}
    	b.mRightChild=A;
    	A.mParent=b;
    	A.mLeftChild=br;
    	if(br!=null)
    	br.mParent=A;
    	
    	
    	
        updateAfterLL(A);
       
    }

    protected void updateAfterLL(IBTN node) {
     
    }
  
    protected void LR(IBTN A) {
        // Write your code here
        int x;
    	IBTN B= (IBTN) A.mLeftChild;
        x=before_LR_RL((IBTN) B.mRightChild);
    	RR(B);
    	LL(A);
    	after_LR_RL((IBTN) A.mParent,x);
   
    }

    protected void RL(IBTN A) {
        // Write your code here
    	int x;
    	IBTN B=(IBTN) A.mRightChild;
        x=before_LR_RL((IBTN) B.mLeftChild);
    	LL(B);
    	RR(A);
    	after_LR_RL((IBTN) A.mParent,x);
    	
    }
    protected int before_LR_RL(IBTN node) {return 0;}
   
    protected void after_LR_RL(IBTN node,int x) {}
    
}
