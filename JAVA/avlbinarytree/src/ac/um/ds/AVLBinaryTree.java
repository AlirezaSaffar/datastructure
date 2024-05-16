package ac.um.ds;

@SuppressWarnings("unchecked")
public class AVLBinaryTree<T, IBTN extends InternalAVLBinaryTreeNode<T>>

        extends InorderPreservingBinaryTree<T, IBTN> {
	    private IBTN curr;
    public AVLBinaryTree() {
        super();
        this.mInorderEnd.mBalanceFactor=0;
    }

    @Override
    protected IBTN createInternalBinaryTreeNodeInstance() {
        return (IBTN) new InternalAVLBinaryTreeNode<T>();
    }

    @Override
    protected void updateAfterInsert(IBTN newNode) {
        // Write your code here
    	this.rotateposition=true;
    	IBTN temp;
    	boolean y,x,check;
    	check=false;
    	
    	x=true;
    	y=true;
    	
    	curr=newNode;
    	while(curr!=this.getRootNode().mActualNode) {
    		temp=curr;
    		y=x;
    		x= curr.mParent.mLeftChild==curr;
    		curr=(IBTN) curr.mParent;
    	
    		if(curr.mLeftChild==temp) {curr.mBalanceFactor++;}
    		if(curr.mRightChild==temp) {curr.mBalanceFactor--;}
    	
    		if(curr.mBalanceFactor==0) {break;}
    		if(curr.mBalanceFactor==2||curr.mBalanceFactor==-2) {check=true;  break;}
    	}
        if((curr.mBalanceFactor>1||curr.mBalanceFactor<-1)&&check) {
    	if(x&&y){LL(curr);}
    	if(!x&&!y){RR(curr);}
    	if(!x&&y){RL(curr);}
    	if(x&&!y){LR(curr);}
    	}
    }

    @Override
    protected void updateAfterDelete(IBTN node) {
        // Write your code here
    	
    	if(node.mBalanceFactor==1||node.mBalanceFactor==-1) return;
        curr=node;
    	IBTN temp=null;
    	int x=0;
    	boolean y=false;
    	if(node.mBalanceFactor==2)y=false;
    	if(node.mBalanceFactor==-2)y=true;
    	boolean f=false;
    	while(curr!=this.getRootNode().mActualNode.mParent) { 
             if(f) {
        	if(y) {curr.mBalanceFactor--;}
        	if(!y) {curr.mBalanceFactor++;}
             }
    	     if((curr.mBalanceFactor==1||curr.mBalanceFactor==-1)) break;	
    		 if(curr.mBalanceFactor==2||curr.mBalanceFactor==-2) {
    			if(!y) {temp=(IBTN) curr.mLeftChild;
    			if(temp==null)break;
    			x=temp.mBalanceFactor;}
        		if(y) {temp=(IBTN) curr.mRightChild;x=temp.mBalanceFactor;}
         if(y&&x==0){this.rotateposition=false;RR(curr);break;}
         if(y&&x==1){RL(curr);}
         if(y&&x==-1){this.rotateposition=true;RR(curr);}
         if(!y&&x==0){this.rotateposition=false;LL(curr);break;}
         if(!y&&x==1){this.rotateposition=true;LL(curr);}
         if(!y&&x==-1){LR(curr);}
        			}
    		y= curr.mParent.mRightChild!=curr;
    		curr= (IBTN) curr.getParent();
    		f=true;
    	}
    	  
    }

    @Override
    protected void updateBeforeDelete(IBTN node) {
        // Write your code here
    	
    	
    	IBTN temp=node;
    	curr=(IBTN) node.mParent;
    
    	if(curr.mLeftChild==temp) {curr.mBalanceFactor--;}
    	if(curr.mRightChild==temp) {curr.mBalanceFactor++;}
    	
    	
    	
    	
    }

    @Override
    protected void updateAfterLL(IBTN node) {
        // Write your code here
    	
    	if(!this.rotateposition) {
    		node.mBalanceFactor=1;
        	curr=(IBTN) node.mParent;
        	curr.mBalanceFactor=-1;}else {
        		node.mBalanceFactor=0;
            	curr=(IBTN) node.mParent;
            	curr.mBalanceFactor=0;
        		
        	}
    
    }

    @Override
    protected void updateAfterRR(IBTN node) {
        // Write your code here
    	
    	
    	if(!this.rotateposition) {
        node.mBalanceFactor=1;
        curr=(IBTN) node.mParent;
        curr.mBalanceFactor=-1;}else {
        node.mBalanceFactor=0;
        curr=(IBTN) node.mParent;
        curr.mBalanceFactor=0;
            		
            	}
        
        }
    @Override
    protected int before_LR_RL(IBTN node){
    	return node.mBalanceFactor;
    }
    
    @Override
    protected void after_LR_RL(IBTN node,int x){
    	IBTN cl,cr;
    	cl=(IBTN) node.mLeftChild;
    	cr=(IBTN) node.mRightChild;
    	if(x==0) {
    		cl.mBalanceFactor=0;
    		cr.mBalanceFactor=0;
    	}
    	if(x==1) {
    		cl.mBalanceFactor=0;
    		cr.mBalanceFactor=-1;
    	}
    	if(x==-1) {
    		cl.mBalanceFactor=1;
    		cr.mBalanceFactor=0;
    	}
    	
    	node.mBalanceFactor=0;
    }
   

    public int getBalanceFactor(BinaryTreeNode<T, IBTN> node) {
        return getActualNode(node).getBalanceFactor();
    }
}
