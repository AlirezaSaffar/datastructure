package ac.um.ds;

import java.util.Iterator;
import java.util.Stack;

public class BinaryTreeBackwardInorderIterator<T, IBTN extends InternalBinaryTreeNode<T> > implements Iterator<T> {
	private IBTN mCurrentNode;
	private Stack<IBTN> mParentsStack;
	private BinaryTree<T, IBTN> mBinaryTree;
public static int ch=0;
	//constructors:
	public BinaryTreeBackwardInorderIterator()
	{
	}

	// The binaryTree parameter is used to initialize the mCurrentNode to
	// the appropriate dummy root. In addition, it is used to initialize mParentsStack	
	public BinaryTreeBackwardInorderIterator(BinaryTree<T, IBTN> binaryTree)
	{
		// Write your code here
		this.mParentsStack= new Stack<IBTN>();
		this.mBinaryTree=binaryTree;
		this.mCurrentNode=(IBTN) mBinaryTree.mInorderEnd;
		this.mParentsStack.add((IBTN) mBinaryTree.dummy1);
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
		while(temp1.mRightChild!=null) {
			temp1=temp1.mRightChild;
		}
		aa= (T) temp1.mData;
		}
		InternalBinaryTreeNode temp;
		ch=1;
		if(this.mCurrentNode.mLeftChild!=null) {
			this.mParentsStack.add(mCurrentNode);
		this.mCurrentNode= (IBTN) this.mCurrentNode.mLeftChild;
		while(this.mCurrentNode.mRightChild!=null) {
			this.mParentsStack.add(mCurrentNode);
			this.mCurrentNode= (IBTN) this.mCurrentNode.mRightChild;
		}
		if(g==0) {return  ans;}else {return aa;}
		}else {
			while(true) {
				temp=this.mParentsStack.pop();
				if(temp.mRightChild==this.mCurrentNode) {break;}
				this.mCurrentNode=(IBTN) temp;
			}
			this.mCurrentNode=(IBTN) temp;
			if(g==0) {return  ans;}else {return aa;}
		}
	}

	@Override
	public boolean hasNext() {
		// Write your code here
		if(this.mCurrentNode.mPosition!=0&&ch==1&&this.mCurrentNode.mPosition!=5 ) { return false;}else {return true;}
	}
}
