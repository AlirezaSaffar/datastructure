package ac.um.ds;

import java.util.Iterator;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BinaryTreeBackwardPreorderIterator<T, IBTN extends InternalBinaryTreeNode> implements Iterator<T> {
	private IBTN mCurrentNode;
	private BinaryTree<T, IBTN> mBinaryTree;
    private static int ch=0;

	// constructors:
	public BinaryTreeBackwardPreorderIterator() {
	}

	public BinaryTreeBackwardPreorderIterator(BinaryTree<T, IBTN> binaryTree) {
		// Write your code here
		this.mBinaryTree=binaryTree;
		this.mCurrentNode=(IBTN) mBinaryTree.dummy4;
		
	}

	// overloading operators:
	@Override
	public T next() {
		// Write your code here
		int g=0;
		T aa=null;
		T ans= (T) this.mCurrentNode.mData;
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
		temp=this.mCurrentNode.mParent;
        if(temp.mLeftChild==this.mCurrentNode) { this.mCurrentNode=(IBTN) temp;
		if(g==0) {return  ans;}else {return aa;}
	    }else {
			if(temp.mLeftChild==null) {this.mCurrentNode=(IBTN) temp;
			if(g==0) {return  ans;}else {return aa;}
			}
		    temp=temp.mLeftChild;
			while(temp.mRightChild!=null||temp.mLeftChild!=null) {
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
	
		if(this.mCurrentNode==this.mBinaryTree.mInorderEnd) {return false;}else {return true;}
	}

	public BinaryTreeNode getBinaryTreeNode() {
		return new BinaryTreeNode(mCurrentNode);
	}
}
