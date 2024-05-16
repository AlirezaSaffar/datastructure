package ac.um.ds;

import java.util.Iterator;


@SuppressWarnings({ "rawtypes", "unchecked" })
public class BinaryTreeBackwardInorderIterator<T, IBTN extends InternalBinaryTreeNode> implements Iterator<T> {
	private IBTN mCurrentNode;
	private BinaryTree<T, IBTN> mBinaryTree;
	private static int ch=0;
	// constructors:
	public BinaryTreeBackwardInorderIterator() {
	}

	public BinaryTreeBackwardInorderIterator(BinaryTree<T, IBTN> binaryTree) {
		// Write your code here
	
		this.mBinaryTree=binaryTree;
		this.mCurrentNode=(IBTN) mBinaryTree.mInorderEnd;
	    ch=0;
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
		while(temp1.mRightChild!=null) {
			temp1=temp1.mRightChild;
		}
		aa= (T) temp1.mData;}
		InternalBinaryTreeNode temp;
		ch=1;
		if(this.mCurrentNode.mLeftChild!=null) {
		this.mCurrentNode= (IBTN) this.mCurrentNode.mLeftChild;
		while(this.mCurrentNode.mRightChild!=null) {
		this.mCurrentNode= (IBTN) this.mCurrentNode.mRightChild;
		}
		if(g==0) {return  ans;}else {return aa;}
		}else {
			while(true) {
				temp=this.mCurrentNode.mParent;
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

		if(this.mCurrentNode==this.mBinaryTree.dummy1) {return false;}else {return true;}
	}

	public BinaryTreeNode getBinaryTreeNode() {
		return new BinaryTreeNode(mCurrentNode);
	}
}
