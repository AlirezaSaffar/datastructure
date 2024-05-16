import ac.um.ds.IndexedBinaryTree;
import ac.um.ds.IndexedLinearList;
import ac.um.ds.BinaryTreeForwardInorderIterator;
import ac.um.ds.BinaryTreeNode;
import ac.um.ds.InternalIndexedBinaryTreeNode;
import java.lang.String;
import java.util.Iterator;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestIndexedLinearList {
    static String inOrder[] = { "g", "d", "k", "h", "l", "b", "a", "e", "i", "c", "f" };
    static String preOrder[] = { "a", "l", "d", "g", "h", "k", "b", "c", "e", "i", "f" };
    static String postOrder[] = { "g", "k", "h", "d", "b", "l", "i", "e", "f", "c", "a" };
    static String deleted[] = { "g", "d", "k", "l", "b", "a", "i", "c" };
    static String deletedIndexedList[] = { "g", "d", "k", "l", "b", "i", "c" };
    static int leftSize[] = { 0, 1, 0, 1, 4, 0, 6, 0, 0, 2, 0 };
    static int leftSizeAfterDelete[] = { 0, 1, 0, 3, 0, 5, 0, 1 };
    static int arrSize = 11;

    private static boolean insertCheck(IndexedBinaryTree<String, InternalIndexedBinaryTreeNode<String>> bt)
            throws IllegalAccessException {

        boolean result = true;

        bt.insertRootNode("a");
        BinaryTreeNode node = bt.getHeaderRootNode().getRightChild();

        BinaryTreeNode btna = bt.getRootNode();

        if (!node.equals(btna.getParent())) {
            result = false;
            return result;
        }

        bt.insertLeftChild(btna, "l");
        bt.insertRightChild(btna, "c");

        BinaryTreeNode btnl = btna.getLeftChild();
        BinaryTreeNode btnc = btna.getRightChild();

        if (!btnc.getParent().equals(btna) || !btnl.getParent().equals(btna)) {

            result = false;
            return result;
        }

        bt.insertLeftChild(btnl, "d");
        bt.insertRightChild(btnl, "b");
        bt.insertLeftChild(btnc, "e");
        bt.insertRightChild(btnc, "f");

        BinaryTreeNode btnd = btnl.getLeftChild();
        BinaryTreeNode btne = btnc.getLeftChild();

        if (!btnd.getParent().equals(btnl) || !btne.getParent().equals(btnc)) {
            result = false;
            return result;
        }
        bt.insertLeftChild(btnd, "g");
        bt.insertRightChild(btnd, "h");
        bt.insertRightChild(btne, "i");

        try {
            bt.insertLeftChild(btnd, "q");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.toString());
        }
        BinaryTreeNode btnh = btnd.getRightChild();

        if (!btnh.getParent().equals(btnd)) {
            result = false;
            return result;
        }
        bt.insertLeftChild(btnh, "k");

        return result;
    }

    private static boolean inOrderCheck(IndexedBinaryTree<String, InternalIndexedBinaryTreeNode<String>> bt) {
        boolean result = true;
        Iterator<String> itr;
        int i;
        System.out.println("\n**Inorder Iteration:\nYours\tCorrect");
        itr = bt.forwardInorderIterator();
        for (i = 0; itr.hasNext(); i++) {
            String temp = itr.next();
            System.out.println(temp + " \t" + inOrder[i]);
            if (!temp.equals(inOrder[i])) {
                result = false;
                break;
            }
        }
        if (i != arrSize) {
            System.out.println("Iteration terminated immaturely.");
        }
        return result;
    }

    private static boolean inOrderReverseCheck(IndexedBinaryTree<String, InternalIndexedBinaryTreeNode<String>> bt) {
        boolean result = true;
        Iterator<String> itr;
        int i;
        System.out.println("\n**Inorder Reverse Iteration:\nYours\tCorrect");
        itr = bt.backwardInorderIterator();
        for (i = arrSize - 1; itr.hasNext(); i--) {
            String temp = itr.next();
            System.out.println(temp + " \t" + inOrder[i]);
            if (!temp.equals(inOrder[i])) {
                result = false;
                break;
            }
        }
        if (i != arrSize) {
            System.out.println("Iteration terminated immaturely.");
        }
        return result;
    }

    private static boolean preOrderCheck(IndexedBinaryTree<String, InternalIndexedBinaryTreeNode<String>> bt) {
        boolean result = true;
        Iterator<String> itr;
        int i;
        System.out.println("\n**PreOrder Iteration:\nYours\tCorrect");
        itr = bt.forwardPreorderIterator();
        for (i = 0; itr.hasNext(); i++) {
            String temp = itr.next();
            System.out.println(temp + " \t" + preOrder[i]);
            if (!temp.equals(preOrder[i])) {
                result = false;
                break;
            }
        }
        if (i != arrSize) {
            System.out.println("Iteration terminated immaturely.");
        }
        return result;
    }

    private static boolean preOrderReverseCheck(IndexedBinaryTree<String, InternalIndexedBinaryTreeNode<String>> bt) {
        boolean result = true;
        Iterator<String> itr;
        int i;
        System.out.println("\n**PreOrder Reverse Iteration:\nYours\tCorrect");
        itr = bt.backwardPreorderIterator();
        for (i = arrSize - 1; itr.hasNext(); i--) {
            String temp = itr.next();
            System.out.println(temp + " \t" + preOrder[i]);
            if (!temp.equals(preOrder[i])) {
                result = false;
                break;
            }
        }
        if (i != arrSize) {
            System.out.println("Iteration terminated immaturely.");
        }
        return result;
    }

    private static boolean postOrderCheck(IndexedBinaryTree<String, InternalIndexedBinaryTreeNode<String>> bt) {
        boolean result = true;
        Iterator<String> itr;
        int i;
        System.out.println("\n**PostOrder Iteration:\nYours\tCorrect");
        itr = bt.forwardPostorderIterator();
        for (i = 0; itr.hasNext(); i++) {
            String temp = itr.next();
            System.out.println(temp + " \t" + postOrder[i]);
            if (!temp.equals(postOrder[i])) {
                result = false;
                break;
            }
        }
        if (i != arrSize) {
            System.out.println("Iteration terminated immaturely.");
        }
        return result;
    }

    private static boolean postOrderReverseCheck(IndexedBinaryTree<String, InternalIndexedBinaryTreeNode<String>> bt) {
        boolean result = true;
        Iterator<String> itr;
        int i;
        System.out.println("\n**PostOrder Reverse Iteration:\nYours\tCorrect");
        itr = bt.backwardPostorderIterator();
        for (i = arrSize - 1; itr.hasNext(); i--) {
            String temp = itr.next();
            System.out.println(temp + " \t" + postOrder[i]);
            if (!temp.equals(postOrder[i])) {
                result = false;
                break;
            }
        }
        if (i != arrSize) {
            System.out.println("Iteration terminated immaturely.");
        }
        return result;
    }

    private static boolean leftSizeCheck(IndexedBinaryTree<String, InternalIndexedBinaryTreeNode<String>> bt,
            int status) {
        boolean result = true;
        BinaryTreeForwardInorderIterator<String, InternalIndexedBinaryTreeNode<String>> itr;
        int i;
        System.out.println("\n**left size :\nYours\tCorrect");
        itr = (BinaryTreeForwardInorderIterator<String, InternalIndexedBinaryTreeNode<String>>) bt
                .forwardInorderIterator();
        for (i = 0; itr.hasNext(); i++) {
            if (status == 1) {
                int temp = bt.getLeftSize(itr.getBinaryTreeNode());
                itr.next();
                System.out.println(temp + " \t" + leftSize[i]);
                if (temp != leftSize[i]) {
                    result = false;
                    break;
                }
            } else {
                int temp = bt.getLeftSize(itr.getBinaryTreeNode());
                itr.next();
                System.out.println(temp + " \t" + leftSizeAfterDelete[i]);
                if (temp != leftSizeAfterDelete[i]) {
                    result = false;
                    break;
                }
            }

        }
        if (i != arrSize - 3) {
            System.out.println("Iteration terminated immaturely.");
        }
        return result;
    }

    private static boolean deleteCheck(IndexedBinaryTree<String, InternalIndexedBinaryTreeNode<String>> bt)
            throws IllegalAccessException {
        boolean result = true;

        BinaryTreeNode btna = bt.getRootNode();

        BinaryTreeNode btnl = btna.getLeftChild();
        BinaryTreeNode btnc = btna.getRightChild();
        BinaryTreeNode btnd = btnl.getLeftChild();
        BinaryTreeNode btnk = btnd.getRightChild().getLeftChild();
        BinaryTreeNode btni = btnc.getLeftChild().getRightChild();

        bt.deleteRightChild(btnd);
        bt.deleteRightChild(btnc);
        bt.deleteLeftChild(btnc);
        try {
            bt.deleteLeftChild(btna);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.toString());
        }

        if (!btni.getParent().equals(btnc) || !btnk.getParent().equals(btnd)) {
            result = false;
            return result;
        }

        Iterator<String> itr;
        int i;
        System.out.println("\n**Inorder Iteration:\nYours\tCorrect");
        itr = bt.forwardInorderIterator();
        for (i = 0; itr.hasNext(); i++) {
            String temp = itr.next();
            System.out.println(temp + " \t" + deleted[i]);
            if (!temp.equals(deleted[i])) {
                result = false;
                break;
            }
        }
        if (i != arrSize - 3) {
            System.out.println("Iteration terminated immaturely.");
        }
        return result;
    }

    private static boolean insertIndexedLinearListCheck(IndexedLinearList<String> ill) throws IllegalAccessException {
        boolean result = true;
        ill.insert(0, "a");
        ill.insert(1, "c");
        ill.insert(2, "f");
        ill.insert(0, "l");
        ill.insert(0, "d");
        ill.insert(0, "g");
        ill.insert(2, "h");
        ill.insert(2, "k");
        ill.insert(5, "b");
        ill.insert(7, "e");
        ill.insert(8, "i");

        ill.drawTree();
        System.out.println();
        System.out.println();

        System.out.println("\n**indexed linear list :\nYours\tCorrect");
        for (int i = 0; i < ill.size(); i++) {
            System.out.println(ill.get(i) + "\t" + inOrder[i]);
            if (!ill.get(i).equals(inOrder[i])) {
                result = false;
                break;
            }
        }
        return result;

    }

    private static boolean deleteIndexedLinearListCheck(IndexedLinearList<String> ill) throws IllegalAccessException {

        boolean result = true;
        ill.remove(3);
        ill.remove(9);
        ill.remove(6);
        ill.remove(5);

        ill.drawTree();
        System.out.println();
        System.out.println();
        System.out.println("\n**indexed linear list :\nYours\tCorrect");

        for (int i = 0; i < ill.size(); i++) {
            System.out.println(ill.get(i) + "\t" + deletedIndexedList[i]);
            if (!ill.get(i).equals(deletedIndexedList[i])) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IllegalAccessException {
        IndexedBinaryTree<String, InternalIndexedBinaryTreeNode<String>> bt = new IndexedBinaryTree<>();
        IndexedLinearList<String> ill = new IndexedLinearList<String>();

        int input;
        boolean result = false;

        for (input = 0; input < 12; input++) {
            switch (input) {
                case 0:
                    System.out.println("0: insert");
                    result = insertCheck(bt);
                    System.out.println(bt.toString());
                    break;
                case 1:
                    System.out.println();
                    System.out.println("1: inOrder Iteration");
                    result = inOrderCheck(bt);
                    break;
                case 2:
                    System.out.println();
                    System.out.println("2: inOrder Reverse Iteration");
                    result = inOrderReverseCheck(bt);
                    break;
                case 3:
                    System.out.println();
                    System.out.println("3: preOrder Iteration");
                    result = preOrderCheck(bt);
                    break;
                case 4:
                    System.out.println();
                    System.out.println("4: preOrder Reverse Iteration");
                    result = preOrderReverseCheck(bt);
                    break;
                case 5:
                    System.out.println();
                    System.out.println("5: postOrder Iteration");
                    result = postOrderCheck(bt);
                    break;
                case 6:
                    System.out.println();
                    System.out.println("6: postOrder Reverse Iteration");
                    result = postOrderReverseCheck(bt);
                    break;
                case 7:
                    System.out.println();
                    System.out.println("7: left size check ");
                    result = leftSizeCheck(bt, 1);
                    break;
                case 8:
                    System.out.println();
                    System.out.println("8: delete nodes");
                    result = deleteCheck(bt);
                    System.out.println(bt.toString());
                    break;
                case 9:
                    System.out.println();
                    System.out.println("9: left size check after delete");
                    result = leftSizeCheck(bt, 2);
                    break;
                case 10:
                    System.out.println();
                    System.out.println("10: test indexed linear list insert");
                    result = insertIndexedLinearListCheck(ill);
                    break;
                case 11:
                    System.out.println();
                    System.out.println("11: test indexed linear list remove");
                    result = deleteIndexedLinearListCheck(ill);
                    break;
            }
            if (result)
                System.out.println("** That was correct!");
            else {
                System.out.println("** Doesn't match.");
                System.out.println("Your code did not pass the tests.");
                return;
            }
        }
        System.out.println("Your code passed all the tests.");
    }
}
