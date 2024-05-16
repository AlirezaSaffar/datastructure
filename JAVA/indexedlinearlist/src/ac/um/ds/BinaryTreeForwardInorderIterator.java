package ac.um.ds;

import java.util.Iterator;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BinaryTreeForwardInorderIterator<T, IBTN extends InternalBinaryTreeNode> implements Iterator<T> {
	private IBTN mCurrentNode;
	private BinaryTree<T, IBTN> mBinaryTree;
    private static int ch=0;
	// constructors:
	public BinaryTreeForwardInorderIterator() {
	}

	public BinaryTreeForwardInorderIterator(BinaryTree<T, IBTN> binaryTree) {
		// Write your code here
		this.mBinaryTree=binaryTree;
	    this.mCurrentNode=(IBTN) this.mBinaryTree.root;
		while(mCurrentNode.getLeftChild() != null) {
		mCurrentNode = (IBTN) mCurrentNode.getLeftChild();
		}
	}

	// overloading operators:
	@Override
	public T next() {
		// Write your code here
		InternalBinaryTreeNode temp;
		int g=0;
		T aa=null;
		T ans= (T) this.mCurrentNode.mData;
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
		this.mCurrentNode= (IBTN) this.mCurrentNode.mRightChild;
		while(this.mCurrentNode.mLeftChild!=null) {
			this.mCurrentNode= (IBTN) this.mCurrentNode.mLeftChild;
		}
		if(g==0) {return  ans;}else {return aa;}
	     }else {
			while(true) {
			    temp=this.mCurrentNode.mParent;
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
	
		if(this.mCurrentNode==this.mBinaryTree.mInorderEnd) {return false;}else {return true;}
	}

	public BinaryTreeNode getBinaryTreeNode() {
		return new BinaryTreeNode(mCurrentNode);
	}
}

