package ac.um.ds;

public class InternalBinaryTreeNode<T> {
    protected T mData;
    protected InternalBinaryTreeNode<T> mParent;
    protected InternalBinaryTreeNode<T> mLeftChild;
    protected InternalBinaryTreeNode<T> mRightChild;
   
    
    public InternalBinaryTreeNode() {
        mLeftChild = null;
        mRightChild = null;
        mParent = null;
    }

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        mData = data;
    }

    public InternalBinaryTreeNode<T> getLeftChild() {
        return mLeftChild;
    }

    public InternalBinaryTreeNode<T> getRightChild() {
        return mRightChild;
    }

    public InternalBinaryTreeNode<T> getParent() {
        return mParent;
    }

}
