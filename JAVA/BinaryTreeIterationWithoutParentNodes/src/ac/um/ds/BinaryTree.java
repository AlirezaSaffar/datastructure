package ac.um.ds;

import java.util.Iterator;

public class BinaryTree<T, IBTN extends InternalBinaryTreeNode<T> >
{
    private int mSize;
	//Write your code here (Add appropriate members to the BinaryTree class.
    protected   InternalBinaryTreeNode dummy1 ;
    protected   InternalBinaryTreeNode mInorderEnd ;
    protected   InternalBinaryTreeNode dummy3  ;
    protected   InternalBinaryTreeNode dummy4 ;
    protected   InternalBinaryTreeNode dummy5 ;
    protected   InternalBinaryTreeNode root;
	
    public BinaryTree () {
		//Write your code here 
    	this.dummy1=  createInternalBinaryTreeNodeInstance();
    	this.mInorderEnd= createInternalBinaryTreeNodeInstance();
    	this.dummy3= createInternalBinaryTreeNodeInstance();
    	this.dummy4= createInternalBinaryTreeNodeInstance();
    	this.dummy5= createInternalBinaryTreeNodeInstance();
    	this.dummy1.mRightChild=this.mInorderEnd;
    	this.dummy1.mLeftChild=this.dummy3;
    	this.mInorderEnd.mRightChild=this.dummy4;
    	this.mSize=0;
    	this.dummy1.mPosition=1;
    	this.mInorderEnd.mPosition=2;
    	this.dummy3.mPosition=3;
    	this.dummy4.mPosition=4;
    }

    protected IBTN createInternalBinaryTreeNodeInstance()
    {
        return (IBTN) new InternalBinaryTreeNode<T>();
    }
    public int size() { return mSize; }

    boolean	isEmpty()
    {
        return mInorderEnd.mLeftChild == null;
    }

    public void insertRootNode(T data) {
		//Write your code here 	
    	this.root=  createInternalBinaryTreeNodeInstance();
    	this.root.setData(data);
    	this.mSize=1;
        this.root.mPosition=5;
    	this.dummy5.mRightChild=root;
    	this.mInorderEnd.mLeftChild=root;
    }

    public BinaryTreeNode<T, IBTN> getRootNode()
    {
        //Write your code here 
    	 return new BinaryTreeNode(mInorderEnd.mLeftChild);
    }

    public void insertLeftChild(BinaryTreeNode<T, IBTN> parentNode, T data) {
        //Write your code here
    	try {
    		if(parentNode.mActualNode.mLeftChild!=null) {
    			RuntimeException e= new RuntimeException();
    			throw e;
    		}
    		this.mSize++;
    	InternalBinaryTreeNode newnode = new InternalBinaryTreeNode();
    	newnode.setData(data);
    	parentNode.mActualNode.mLeftChild=newnode;
    	
    	}catch(RuntimeException e) {return;}
    }

    public void insertRightChild(BinaryTreeNode<T, IBTN> parentNode, T data) {
        //Write your code here
    	try {
    		if(parentNode.mActualNode.mRightChild!=null) {
    			RuntimeException e= new RuntimeException();
    			throw e;
    		}
    		this.mSize++;
    	InternalBinaryTreeNode newnode = new InternalBinaryTreeNode();
    	newnode.setData(data);
    	parentNode.mActualNode.mRightChild=newnode;}catch(RuntimeException e) {
    		return;
    	}
    }

    public void deleteLeftChild(BinaryTreeNode<T, IBTN> parent)  // Only leaf nodes and nodes with degree 1 can be deleted. If a degree 1 node is deleted, it is replaced by its subtree.
    {
        IBTN parentNode = parent.mActualNode;
        IBTN theNode = (IBTN) parent.getLeftChild().mActualNode;
        deleteNode(parentNode, theNode);
    }

    public void deleteRightChild(BinaryTreeNode<T, IBTN> parent)  // Only leaf nodes and nodes with degree 1 can be deleted. If a degree 1 node is deleted, it is replaced by its subtree.
    {
        IBTN parentNode = parent.mActualNode;
        IBTN theNode = (IBTN) parent.getRightChild().mActualNode;
        deleteNode(parentNode, theNode);
    }

    protected void deleteNode(IBTN parentNode, IBTN theNode)  // Only leaf nodes and nodes with degree 1 can be deleted. If a degree 1 node is deleted, it is replaced by its subtree.
    {
        // Write your code here
    	try {
        	if(theNode.mRightChild==null&&theNode.mLeftChild==null) { this.mSize--;
        		if(parentNode.mLeftChild==theNode) {parentNode.mLeftChild=null;}
        		if(parentNode.mRightChild==theNode) {parentNode.mRightChild=null;}
        	}
        	if(theNode.mRightChild==null&&theNode.mLeftChild!=null) { this.mSize--;
        		if(parentNode.mLeftChild==theNode) {parentNode.mLeftChild=theNode.mLeftChild;}
        		if(parentNode.mRightChild==theNode) {parentNode.mRightChild=theNode.mLeftChild;;}
        	}
        	if(theNode.mRightChild!=null&&theNode.mLeftChild==null) { this.mSize--;
        		if(parentNode.mLeftChild==theNode) {parentNode.mLeftChild=theNode.mRightChild;}
        		if(parentNode.mRightChild==theNode) {parentNode.mRightChild=theNode.mRightChild;}
        	}
        	if(theNode.mRightChild!=null&&theNode.mLeftChild!=null) {
        		RuntimeException e= new RuntimeException();
    			throw e;
        	}
        	
        	}catch(RuntimeException e) { return;
        	}
    }

    public Iterator<T> forwardInorderIterator() {
        // Write your code here
    	Iterator t= new BinaryTreeForwardInorderIterator<T,IBTN>(this);
    	return t;
    }

    public Iterator<T> backwardInorderIterator() {
		// Write your code here
    	Iterator t= new BinaryTreeBackwardInorderIterator<T,IBTN>(this);
    	return t;
	}

    public Iterator<T> forwardPreorderIterator() {
        // Write your code here
    	Iterator t= new BinaryTreeForwardPreorderIterator<T,IBTN>(this);
    	return t;
    }

    public Iterator<T> backwardPreorderIterator() {
		// Write your code here
    	Iterator t= new BinaryTreeBackwardPreorderIterator<T,IBTN>(this);
    	return t;
	}

    public Iterator<T> forwardPostorderIterator() {
        // Write your code here
    	Iterator t= new BinaryTreeForwardPostorderIterator<T,IBTN>(this);
    	return t;
    }

    public Iterator<T> backwardPostorderIterator() {
		// Write your code here
    	Iterator t= new BinaryTreeBackwardPostorderIterator<T,IBTN>(this);
    	return t;
	}
	
    protected IBTN getActualNode(BinaryTreeNode<T, IBTN> node) 
	{
		return node.mActualNode;
	}

     public String draw() {
        String str = "";
        int maxDepth = 11;
        int depth = depthCalc(getRootNode().mActualNode, 1);
        depth = depth * 2 - 1;

        if (depth > maxDepth) {
            return "Can't draw, the height of the tree is greater than " + (maxDepth + 1) / 2;
        }

        String[][] map = new String[depth][];
        for (int i = 0; i < depth; i++) {
            map[i] = new String[160];
            for (int j = 0; j < 160; j++)
                map[i][j] = " ";
        }

        recursiveDraw(getRootNode().mActualNode, map, 80, 0);

        for (int i = 0; i < depth; i++)
            for (int j = 0; j < 160; j++)
                str += map[i][j];

        return str;

    }

    @Override
    public String toString() {
        String str = draw();
        return str;
    }

    public int depthCalc(IBTN root, int depth)
    {
        int res = depth;
        if (root.getRightChild() != null){
            int rightDepth = depthCalc((IBTN)root.getRightChild(), depth + 1);
            res = (res > rightDepth) ? res : rightDepth;
        }
        if(root.getLeftChild() != null){
            int leftDepth = depthCalc((IBTN)root.getLeftChild(), depth + 1);
            res = (res > leftDepth) ? res : leftDepth;
        }
        return res;
    }

    public void recursiveDraw(IBTN root, String lines[][], int x, int y)
    {
        int des = 1;
        for (int i = 0; i < y / 2 + 2; i++)
            des *= 2;
        des = 160 / des;
        //root:

        lines[y][x] =  root.getData().toString();
        //left child:
        if (root.getLeftChild() != null){
            for (int i = 0; i < des; i++)
                lines[y + 1][x - i] = "-";
            lines[y + 1][x] = "|";
            recursiveDraw((IBTN)root.getLeftChild(), lines, x - des, y + 2);
        }
        //right child:
        if (root.getRightChild() != null){
            for (int i = 0; i < des; i++)
                lines[y + 1][x + i] = "-";
            lines[y + 1][x] = "|";
            recursiveDraw((IBTN)root.getRightChild(), lines, x + des, y + 2);
        }

    }

}

