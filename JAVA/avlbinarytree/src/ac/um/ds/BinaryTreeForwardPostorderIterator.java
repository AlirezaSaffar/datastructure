package ac.um.ds;

import java.util.Iterator;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BinaryTreeForwardPostorderIterator<T, IBTN extends InternalBinaryTreeNode> implements Iterator<T> {
	private IBTN mCurrentNode;
	private BinaryTree<T, IBTN> mBinaryTree;
    private static int ch=0;
	// constructors:
	public BinaryTreeForwardPostorderIterator() {
	}

	public BinaryTreeForwardPostorderIterator(BinaryTree<T, IBTN> binaryTree) {
		// Write your code here
		this.mBinaryTree=binaryTree;
		this.mCurrentNode=(IBTN) mBinaryTree.mInorderEnd;
	    this.mCurrentNode=(IBTN) this.mBinaryTree.mInorderEnd.mLeftChild;
		while(this.mCurrentNode.mRightChild!=null||this.mCurrentNode.mLeftChild!=null) {
		if(this.mCurrentNode.mLeftChild!=null) {this.mCurrentNode=(IBTN) this.mCurrentNode.mLeftChild;continue;}
	    this.mCurrentNode=(IBTN) this.mCurrentNode.mRightChild;
		}
			
	}

	// overloading operators:
	@Override
	public T next() {
		// Write your code here
		int g=0;
		T aa=null;
		T ans= (T) this.mCurrentNode.mData;
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
        temp=this.mCurrentNode.mParent;
        if(temp.mRightChild==this.mCurrentNode) { this.mCurrentNode=(IBTN) temp; 
		if(g==0) {return  ans;}else {return aa;}
	     }else {
			if(temp.mRightChild==null) {this.mCurrentNode=(IBTN) temp; 
			if(g==0) {return  ans;}else {return aa;}
		}
		
			temp=temp.mRightChild;
			while(temp.mRightChild!=null||temp.mLeftChild!=null) {
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
	
		if(this.mCurrentNode==this.mBinaryTree.dummy4) {return false;}else {return true;}
	}

	public BinaryTreeNode getBinaryTreeNode() {
		return new BinaryTreeNode(mCurrentNode);
	}
}
