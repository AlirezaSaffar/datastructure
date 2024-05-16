package ac.um.ds;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BinaryTreeNode<T, IBTN extends InternalBinaryTreeNode> {
    protected IBTN mActualNode;

    protected BinaryTreeNode(IBTN node) {
        mActualNode = node;
    }

    public void setData(T data) {
        mActualNode.setData(data);
    }

    public T getData() {
        return (T) mActualNode.getData();
    }

    public boolean hasLeftChild() {
        return mActualNode.getLeftChild() != null;
    }

    public BinaryTreeNode getLeftChild() {
        return new BinaryTreeNode((IBTN) mActualNode.getLeftChild());
    }

    public boolean hasRightChild() {
        return mActualNode.getRightChild() != null;
    }

    public BinaryTreeNode getRightChild() {
        return new BinaryTreeNode((IBTN) mActualNode.getRightChild());
    }

    public BinaryTreeNode getParent() {
        return new BinaryTreeNode((IBTN) mActualNode.getParent());
    }

    public boolean equals(BinaryTreeNode btn) {

        return this.mActualNode.equals(btn.mActualNode);
    }
}
