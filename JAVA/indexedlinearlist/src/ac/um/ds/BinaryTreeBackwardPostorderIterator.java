package ac.um.ds;

import java.util.Iterator;

@SuppressWarnings({ "rawtypes", "unchecked" })

public class BinaryTreeBackwardPostorderIterator<T, IBTN extends InternalBinaryTreeNode> implements Iterator<T> {
	private IBTN mCurrentNode;
	private BinaryTree<T, IBTN> mBinaryTree;
	private static int ch=0;
	// constructors:
	public BinaryTreeBackwardPostorderIterator() {
	}

	public BinaryTreeBackwardPostorderIterator(BinaryTree<T, IBTN> binaryTree) {
		// Write your code here
		this.mBinaryTree=binaryTree;
	    this.mCurrentNode=(IBTN) this.mBinaryTree.root;
	}

	// overloading operators:
	@Override
	public T next() {
		// Write your code here
		int g=0;
		T aa=null;
		T ans= (T) this.mCurrentNode.mData;
		if(ch==0) {
			ch=1; 
	        g=1;
		    aa=ans;
		}
		InternalBinaryTreeNode temp;
		if(this.mCurrentNode.mRightChild!=null) {
		this.mCurrentNode= (IBTN) this.mCurrentNode.mRightChild;
    if(g==0) {return  ans;}else {return aa;}
		}else {
			if(this.mCurrentNode.mLeftChild!=null) {
			this.mCurrentNode= (IBTN) this.mCurrentNode.mLeftChild;
     if(g==0) {return  ans;}else {return aa;}
			}else {
				while(true) {
	                temp=this.mCurrentNode.mParent;
				    if(temp.mRightChild==this.mCurrentNode&&temp.mLeftChild!=null) {break;}
					this.mCurrentNode=(IBTN) temp;
				}
		        this.mCurrentNode=(IBTN) temp.mLeftChild;
				if(g==0) {return  ans;}else {return aa;}
			}
		}
	}

	@Override
	public boolean hasNext() {
		// Write your code here
		if(this.mCurrentNode==this.mBinaryTree.dummy3) {return false;}else {return true;}
	}

	public BinaryTreeNode getBinaryTreeNode() {
		return new BinaryTreeNode(mCurrentNode);
	}
}
