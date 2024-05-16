package ac.um.ds;

import java.util.Iterator;
import java.util.Stack;

public class BinaryTreeForwardPreorderIterator<T, IBTN extends InternalBinaryTreeNode<T> > implements Iterator<T> {
	private IBTN mCurrentNode;
	private Stack<IBTN> mParentsStack;
	private BinaryTree<T, IBTN> mBinaryTree;
    public static int ch=0;
	//constructors:
	public BinaryTreeForwardPreorderIterator()
	{
	}

	// The binaryTree parameter is used to initialize the mCurrentNode to
	// the appropriate dummy root. In addition, it is used to initialize mParentsStack
	public BinaryTreeForwardPreorderIterator(BinaryTree<T, IBTN> binaryTree)
	{
		// Write your code here
		this.mParentsStack= new Stack<IBTN>();
		this.mBinaryTree=binaryTree;
		this.mCurrentNode=(IBTN) mBinaryTree.root;
		this.mParentsStack.add((IBTN) mBinaryTree.mInorderEnd);
	}
	
	//overloading operators:
	@Override
	public T next() {
		// Write your code here
		int g=0;
		T aa=null;
		T ans= this.mCurrentNode.mData;
		if(ch==0) {ch=1;
		InternalBinaryTreeNode temp1;
		temp1=this.mBinaryTree.mInorderEnd.mLeftChild;
		aa=ans;
	    }
		InternalBinaryTreeNode temp;
		if(this.mCurrentNode.mLeftChild!=null) {
			this.mParentsStack.add(mCurrentNode);
			this.mCurrentNode= (IBTN) this.mCurrentNode.mLeftChild;

if(g==0) {return  ans;}else {return aa;}
		}else {
			if(this.mCurrentNode.mRightChild!=null) {
				this.mParentsStack.add(mCurrentNode);
				this.mCurrentNode= (IBTN) this.mCurrentNode.mRightChild;
if(g==0) {return  ans;}else {return aa;}
			}else {
				while(true) {
					temp=this.mParentsStack.pop();
					if(temp.mLeftChild==this.mCurrentNode&&temp.mRightChild!=null) {break;}
					this.mCurrentNode=(IBTN) temp;
					
				}
				mParentsStack.add((IBTN) temp);
				this.mCurrentNode=(IBTN) temp.mRightChild;
				if(g==0) {return  ans;}else {return aa;}
			}
		}
	}

	@Override
	public boolean hasNext() {
		// Write your code here		
		if(this.mCurrentNode.mPosition==4) {return false;}else {return true;}
	}
}
