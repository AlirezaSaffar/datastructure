package ac.um.ds;

import java.util.Iterator;
import java.util.Stack;

public class BinaryTreeBackwardPreorderIterator<T, IBTN extends InternalBinaryTreeNode<T> > implements Iterator<T> {
	private IBTN mCurrentNode;
	private Stack<IBTN> mParentsStack;
	private BinaryTree<T, IBTN> mBinaryTree;
    public static int ch=0;
	//constructors:
	public BinaryTreeBackwardPreorderIterator()
	{
	}
	// The binaryTree parameter is used to initialize the mCurrentNode to
	// the appropriate dummy root. In addition, it is used to initialize mParentsStack	
	public BinaryTreeBackwardPreorderIterator(BinaryTree<T, IBTN> binaryTree)
	{
		// Write your code here
		this.mParentsStack= new Stack<IBTN>();
		this.mBinaryTree=binaryTree;
		this.mCurrentNode=(IBTN) mBinaryTree.dummy4;
		this.mParentsStack.add((IBTN) this.mBinaryTree.mInorderEnd);
		ch=0;
    }
	
	//overloading operators:
	@Override
	public T next() {
		// Write your code here
		int g=0;
		T aa=null;
		T ans= this.mCurrentNode.mData;
		if(ch==0) {ch=1; ans=this.next();g=1;
		InternalBinaryTreeNode temp1;
		temp1=this.mBinaryTree.mInorderEnd.mLeftChild;
		while(temp1.mRightChild!=null||temp1.mLeftChild!=null) {
			if(temp1.mRightChild!=null) {
			temp1=temp1.mRightChild; continue;  }
			temp1=temp1.mLeftChild;
		}
		aa= (T) temp1.mData;
		}
		InternalBinaryTreeNode temp;
		temp = this.mParentsStack.pop();
		if(temp.mLeftChild==this.mCurrentNode) { this.mCurrentNode=(IBTN) temp;
		if(g==0) {return  ans;}else {return aa;}
	    }else {
			if(temp.mLeftChild==null) {this.mCurrentNode=(IBTN) temp;
			if(g==0) {return  ans;}else {return aa;}
			}
			this.mParentsStack.add((IBTN) temp);
			temp=temp.mLeftChild;
			while(temp.mRightChild!=null||temp.mLeftChild!=null) {
				this.mParentsStack.add((IBTN) temp);
				if(temp.mRightChild!=null) {temp=temp.mRightChild;continue;}
				temp=temp.mLeftChild;
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
