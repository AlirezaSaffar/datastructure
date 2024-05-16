package ac.um.ds;

public class InternalBinaryTreeNode<T>
{
    protected T mData;
    protected InternalBinaryTreeNode<T> mLeftChild;
    protected InternalBinaryTreeNode<T> mRightChild;
    protected int mPosition;

    public void InternalBinaryTreeNode()
    {
        mLeftChild = null;
        mRightChild = null;
    }

    public T getData()
    {
        return mData;
    }

    public void setData(T data){ mData = data; }

    public InternalBinaryTreeNode<T> getLeftChild()
    {
        return mLeftChild;
    }

    public InternalBinaryTreeNode<T> getRightChild()
    {
        return mRightChild;
    }

}
