package ac.um.ds;

import java.util.Iterator;
import java.util.Stack;

public class BinaryTreeForwardInorderIterator<T, IBTN extends InternalBinaryTreeNode<T> > implements Iterator<T> {
	private IBTN mCurrentNode;
	private Stack<IBTN> mParentsStack;
	private BinaryTree<T, IBTN> mBinaryTree;
    public static int ch=0;
	//constructors:
	public BinaryTreeForwardInorderIterator()
	{
	}

	// The binaryTree parameter is used to initialize the mCurrentNode to
	// the appropriate dummy root. In addition, it is used to initialize mParentsStack	
	public BinaryTreeForwardInorderIterator(BinaryTree<T, IBTN> binaryTree)
	{
		// Write your code here
		this.mParentsStack= new Stack<IBTN>();
		this.mBinaryTree=binaryTree;
		this.mParentsStack.add((IBTN) this.mBinaryTree.mInorderEnd);
		this.mCurrentNode=(IBTN) mBinaryTree.root;
		while(mCurrentNode.getLeftChild() != null) {
			mParentsStack.push(mCurrentNode);
			mCurrentNode = (IBTN) mCurrentNode.getLeftChild();
		}
		
	}
	
	//overloading operators:
	@Override
	public T next() {
		// Write your code here
		InternalBinaryTreeNode temp;
		int g=0;
		T aa=null;
		T ans= this.mCurrentNode.mData;
		if(ch==0) {ch=1; 
	    g=1;
		InternalBinaryTreeNode temp1;
		temp1=this.mBinaryTree.mInorderEnd.mLeftChild;
		while(temp1.mLeftChild!=null) {
			temp1=temp1.mLeftChild;
		}
		ans=aa;
		aa= (T) temp1.mData;
		}
	    if(this.mCurrentNode.mRightChild!=null) {
			this.mParentsStack.add(mCurrentNode);
		this.mCurrentNode= (IBTN) this.mCurrentNode.mRightChild;
		while(this.mCurrentNode.mLeftChild!=null) {
			this.mParentsStack.add(mCurrentNode);
			this.mCurrentNode= (IBTN) this.mCurrentNode.mLeftChild;
		}
		if(g==0) {return  ans;}else {return aa;}
	    }else {
			while(true) {
				temp=this.mParentsStack.pop();
			    if(temp.mLeftChild==this.mCurrentNode) {break;}
				this.mCurrentNode=(IBTN) temp;
			}
			this.mCurrentNode=(IBTN) temp;
			if(g==0) {return  ans;}else {return aa;}
		}
	}

	@Override
	public boolean hasNext() {
		// Write your code here		
		if(this.mCurrentNode.mPosition==2) {return false;}else {return true;}
	}
}
