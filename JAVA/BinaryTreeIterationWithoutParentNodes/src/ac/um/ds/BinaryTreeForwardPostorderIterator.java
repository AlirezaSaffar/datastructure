package ac.um.ds;

import java.util.Iterator;
import java.util.Stack;

public class BinaryTreeForwardPostorderIterator<T, IBTN extends InternalBinaryTreeNode<T> > implements Iterator<T> {
	private IBTN mCurrentNode;
	private Stack<IBTN> mParentsStack;
	private BinaryTree<T, IBTN> mBinaryTree;
   public static int ch=0;
	//constructors:
	public BinaryTreeForwardPostorderIterator()
	{
	}

	// The binaryTree parameter is used to initialize the mCurrentNode to
	// the appropriate dummy root. In addition, it is used to initialize mParentsStack	
	public BinaryTreeForwardPostorderIterator(BinaryTree<T, IBTN> binaryTree)
	{
		// Write your code here
		this.mParentsStack= new Stack<IBTN>();
		this.mBinaryTree=binaryTree;
		this.mCurrentNode=(IBTN) mBinaryTree.mInorderEnd;
		this.mParentsStack.add((IBTN) this.mBinaryTree.mInorderEnd);
	//	this.mParentsStack.add((IBTN) this.mBinaryTree.dummy2.mLeftChild);
		this.mCurrentNode=(IBTN) this.mBinaryTree.mInorderEnd.mLeftChild;
		while(this.mCurrentNode.mRightChild!=null||this.mCurrentNode.mLeftChild!=null) {
			this.mParentsStack.add((IBTN) this.mCurrentNode);
			if(this.mCurrentNode.mLeftChild!=null) {this.mCurrentNode=(IBTN) this.mCurrentNode.mLeftChild;continue;}
			this.mCurrentNode=(IBTN) this.mCurrentNode.mRightChild;
		}
			
	}

	//overloading operators:
	@Override
	public T next() {
		// Write your code here
		int g=0;
		T aa=null;
		T ans= this.mCurrentNode.mData;
		if(ch==0) {ch=1;
	    g=1;
		InternalBinaryTreeNode temp1;
		temp1=this.mBinaryTree.mInorderEnd.mLeftChild;
		while(temp1.mRightChild!=null||temp1.mLeftChild!=null) {
			if(temp1.mLeftChild!=null) {
			temp1=temp1.mLeftChild; continue;  }
			temp1=temp1.mRightChild;
		}
		aa= (T) temp1.mData;
		}
		InternalBinaryTreeNode temp;
		temp = this.mParentsStack.pop();
		if(temp.mRightChild==this.mCurrentNode) { this.mCurrentNode=(IBTN) temp; 
		if(g==0) {return  ans;}else {return aa;}
	     }else {
			if(temp.mRightChild==null) {this.mCurrentNode=(IBTN) temp; 
			if(g==0) {return  ans;}else {return aa;}
		    }
			this.mParentsStack.add((IBTN) temp);
			temp=temp.mRightChild;
			while(temp.mRightChild!=null||temp.mLeftChild!=null) {
				this.mParentsStack.add((IBTN) temp);
				if(temp.mLeftChild!=null) {temp=temp.mLeftChild;continue;}
				temp=temp.mRightChild;
			}
			this.mCurrentNode=(IBTN) temp;
			if(g==0) {return  ans;}else {return aa;}
		}
	}

	@Override
	public boolean hasNext() {
		// Write your code here
		if(this.mCurrentNode.mPosition==4) {return false;}else {return true;}
	}
}
