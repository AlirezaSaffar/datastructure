package ac.um.ds;

public class InternalAVLBinaryTreeNode<T> extends InternalInorderPreservingBinaryTreeNode<T> {
    protected int mBalanceFactor;

    public InternalAVLBinaryTreeNode() {
        super();
        mBalanceFactor = 0;
    }

    public int getBalanceFactor() {
        return mBalanceFactor;
    }

}
