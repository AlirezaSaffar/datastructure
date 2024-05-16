package ac.um.ds;

public class InternalIndexedBinaryTreeNode<T> extends InternalBinaryTreeNode<T> {
    protected int mLeftSize;
   
    public InternalIndexedBinaryTreeNode() {
        super();
        mLeftSize = 0;
    }

    public int getLeftSize() {
        return mLeftSize;
    }

}
