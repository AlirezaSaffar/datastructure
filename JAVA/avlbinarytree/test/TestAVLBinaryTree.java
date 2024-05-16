import ac.um.ds.*;
import java.lang.String;
import java.util.Iterator;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestAVLBinaryTree {
    static String inOrder[] = { "g", "d", "h", "k", "l", "a", "e", "i", "c" };
    static String preOrder[] = { "e", "h", "d", "g", "l", "k", "a", "c", "i" };
    static String postOrder[] = { "g", "d", "k", "a", "l", "h", "i", "c", "e" };
    static String deleted[] = { "d", "p", "h", "q", "k", "l" };
    static int balanceFactor[] = { 0, 1, 0, 0, 0, 0, 1, 0, 1 };
    static int balanceFactorAfterDelete[] = { -1, 0, 0, 0, 0, 0 };
    static int arrSize = 9;

    private static boolean insertCheck(AVLBinaryTree<String, InternalAVLBinaryTreeNode<String>> avlBt)
            throws IllegalAccessException {
        // e
        // / \
        // h c
        // / \ /
        // d l i
        // g k a
        boolean result = true;
        BinaryTreeNode btnHeaderRoot = avlBt.getHeaderRootNode().getRightChild();
        avlBt.insertRootNode("a");
        BinaryTreeNode btna = avlBt.getRootNode();
        avlBt.insertLeftChild(btna, "l");

        BinaryTreeNode btnl = btna.getLeftChild();

        avlBt.insertLeftChild(btnl, "d");
        https: // open.spotify.com/artist/3lw0h8zKlJwBS3ikOTz3gS
        avlBt.insertRightChild(btna, "c");

        if (!btnl.getParent().equals(btnHeaderRoot) || !btna.getParent().equals(btnl)) {
            result = false;
            return result;
        }

        BinaryTreeNode btnc = btna.getRightChild();
        avlBt.insertLeftChild(btnc, "e");

        avlBt.insertLeftChild(btnc, "i");

        BinaryTreeNode btne = avlBt.getRootNode();
        if (!btnc.getParent().equals(btne) || !btne.getParent().equals(btnHeaderRoot) ||
                !btnl.getParent().equals(btne)) {
            result = false;
            return result;
        }

        BinaryTreeNode btnd = btnl.getLeftChild();
        avlBt.insertLeftChild(btnd, "g");

        avlBt.insertRightChild(btnd, "h");

        try {
            avlBt.insertLeftChild(btnd, "q");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.toString());
        }

        BinaryTreeNode btnh = btnd.getRightChild();

        avlBt.insertRightChild(btnh, "k");

        BinaryTreeNode btnk = btnl.getLeftChild();
        if (!btnh.getParent().equals(btne) || !btnl.getParent().equals(btnh) || !btnk.getParent().equals(btnl)) {
            result = false;
            return result;
        }

        return result;
    }

    private static boolean inOrderCheck(AVLBinaryTree<String, InternalAVLBinaryTreeNode<String>> avlBt) {
        boolean result = true;
        Iterator<String> itr;
        int i;
        System.out.println("\n**Inorder Iteration:\nYours\tCorrect");
        itr = avlBt.forwardInorderIterator();
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

    private static boolean inOrderReverseCheck(AVLBinaryTree<String, InternalAVLBinaryTreeNode<String>> avlBt) {
        boolean result = true;
        Iterator<String> itr;
        int i;
        System.out.println("\n**Inorder Reverse Iteration:\nYours\tCorrect");
        itr = avlBt.backwardInorderIterator();
        for (i = arrSize - 1; itr.hasNext(); i--) {
            String temp = itr.next();
            System.out.println(temp + " \t" + inOrder[i]);
            if (!temp.equals(inOrder[i])) {
                result = false;
                break;
            }
        }
        if (i != -1) {
            System.out.println("Iteration terminated immaturely.");
        }
        return result;
    }

    private static boolean preOrderCheck(AVLBinaryTree<String, InternalAVLBinaryTreeNode<String>> avlBt) {
        boolean result = true;
        Iterator<String> itr;
        int i;
        System.out.println("\n**PreOrder Iteration:\nYours\tCorrect");
        itr = avlBt.forwardPreorderIterator();
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

    private static boolean preOrderReverseCheck(
            AVLBinaryTree<String, InternalAVLBinaryTreeNode<String>> avlBt) {
        boolean result = true;
        Iterator<String> itr;
        int i;
        System.out.println("\n**PreOrder Reverse Iteration:\nYours\tCorrect");
        itr = avlBt.backwardPreorderIterator();
        for (i = arrSize - 1; itr.hasNext(); i--) {
            String temp = itr.next();
            System.out.println(temp + " \t" + preOrder[i]);
            if (!temp.equals(preOrder[i])) {
                result = false;
                break;
            }
        }
        if (i != -1) {
            System.out.println("Iteration terminated immaturely.");
        }
        return result;
    }

    private static boolean postOrderCheck(AVLBinaryTree<String, InternalAVLBinaryTreeNode<String>> avlBt) {
        boolean result = true;
        Iterator<String> itr;
        int i;
        System.out.println("\n**PostOrder Iteration:\nYours\tCorrect");
        itr = avlBt.forwardPostorderIterator();
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

    private static boolean postOrderReverseCheck(
            AVLBinaryTree<String, InternalAVLBinaryTreeNode<String>> avlBt) {
        boolean result = true;
        Iterator<String> itr;
        int i;
        System.out.println("\n**PostOrder Reverse Iteration:\nYours\tCorrect");
        itr = avlBt.backwardPostorderIterator();
        for (i = arrSize - 1; itr.hasNext(); i--) {
            String temp = itr.next();
            System.out.println(temp + " \t" + postOrder[i]);
            if (!temp.equals(postOrder[i])) {
                result = false;
                break;
            }
        }
        if (i != -1) {
            System.out.println("Iteration terminated immaturely.");
        }
        return result;
    }

    private static boolean deleteCheck(AVLBinaryTree<String, InternalAVLBinaryTreeNode<String>> avlBt)
            throws IllegalAccessException {

        boolean result = true;
        // e
        // / \
        // h c
        // / \ /
        // d l i
        // g k a
        BinaryTreeNode btne = avlBt.getRootNode();
        BinaryTreeNode btnc = btne.getRightChild();
        BinaryTreeNode btnh = btne.getLeftChild();
        BinaryTreeNode btnd = btnh.getLeftChild();
        BinaryTreeNode btnl = btnh.getRightChild();
        BinaryTreeNode btnk = btnl.getLeftChild();

        avlBt.deleteLeftChild(btnc);

        if (!btne.getParent().equals(btnh) || !btnl.getParent().equals(btne)) {
            result = false;
            return result;
        }

        avlBt.deleteLeftChild(btnd);

        if (!btnk.getParent().equals(btnh) || !btnh.getParent().equals(btnl)) {
            result = false;
            return result;
        }

        avlBt.insertLeftChild(btnk, "q");

        avlBt.deleteLeftChild(btne);
        avlBt.deleteRightChild(btne);
        if (!btnl.getParent().equals(btnk) || !btnh.getParent().equals(btnk)) {
            result = false;
            return result;
        }

        avlBt.insertRightChild(btnd, "p");
        avlBt.deleteRightChild(btnl);
        if (!btnk.getParent().equals(btnh) || !btnl.getParent().equals(btnk)) {
            result = false;
            return result;
        }

        Iterator<String> itr;
        int i;
        System.out.println("\n**Inorder Iteration:\nYours\tCorrect");
        itr = avlBt.forwardInorderIterator();
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

    private static boolean balanceFactorCheck(AVLBinaryTree<String, InternalAVLBinaryTreeNode<String>> bt,
            int status) {
        boolean result = true;
        BinaryTreeForwardInorderIterator<String, InternalAVLBinaryTreeNode<String>> itr;
        int i;
        System.out.println("\n**balance factor:\nYours\tCorrect");
        itr = (BinaryTreeForwardInorderIterator<String, InternalAVLBinaryTreeNode<String>>) bt
                .forwardInorderIterator();
        for (i = 0; itr.hasNext(); i++) {
            if (status == 1) {
                int temp = bt.getBalanceFactor(itr.getBinaryTreeNode());
                itr.next();
                System.out.println(temp + " \t" + balanceFactor[i]);
                if (temp != balanceFactor[i]) {
                    result = false;
                    break;
                }
            } else {
                int temp = bt.getBalanceFactor(itr.getBinaryTreeNode());
                itr.next();
                System.out.println(temp + " \t" + balanceFactorAfterDelete[i]);
                if (temp != balanceFactorAfterDelete[i]) {
                    result = false;
                    break;
                }
            }

        }
        if (status == 1 && i != arrSize) {
            System.out.println("Iteration terminated immaturely.");
        } else if (i != arrSize - 3 && status == 2)
            System.out.println("Iteration terminated immaturely.");

        return result;
    }

    public static void main(String[] args) throws IllegalAccessException {
        AVLBinaryTree<String, InternalAVLBinaryTreeNode<String>> avlBt = new AVLBinaryTree<>();
        AVLBinaryTree<String, InternalAVLBinaryTreeNode<String>> avlBtRandom = new AVLBinaryTree<>();

        int input;
        boolean result = false;

        for (input = 0; input < 12; input++) {
            switch (input) {
                case 0:
                    System.out.println("0: insert");
                    result = insertCheck(avlBt);
                    System.out.println(avlBt.toString());
                    break;
                case 1:
                    System.out.println();
                    System.out.println("1: inOrder Iteration");
                    result = inOrderCheck(avlBt);
                    break;
                case 2:
                    System.out.println();
                    System.out.println("2: inOrder Reverse Iteration");
                    result = inOrderReverseCheck(avlBt);
                    break;
                case 3:
                    System.out.println();
                    System.out.println("3: preOrder Iteration");
                    result = preOrderCheck(avlBt);
                    break;
                case 4:
                    System.out.println();
                    System.out.println("4: preOrder Reverse Iteration");
                    result = preOrderReverseCheck(avlBt);
                    break;
                case 5:
                    System.out.println();
                    System.out.println("5: postOrder Iteration");
                    result = postOrderCheck(avlBt);
                    break;
                case 6:
                    System.out.println();
                    System.out.println("6: postOrder Reverse Iteration");
                    result = postOrderReverseCheck(avlBt);
                    break;
                case 7:
                    System.out.println();
                    System.out.println("7: balance factor check ");
                    result = balanceFactorCheck(avlBt, 1);
                    break;
                case 8:
                    System.out.println();
                    System.out.println("8: delete nodes");
                    result = deleteCheck(avlBt);
                    System.out.println(avlBt.toString());
                    break;
                case 9:
                    System.out.println();
                    System.out.println("9: balance factor check after delete");
                    result = balanceFactorCheck(avlBt, 2);
                    break;
                case 10:
                    System.out.println();
                    System.out.println("10: random insert test");
                    result = TestRandomInsertAndDelete.testInsertAndDeleteAVL(avlBtRandom, 0, 30);
                    break;
                case 11:
                    System.out.println();
                    System.out.println("11: random delete test");
                    System.out.println("inoeder:");
                   // BinaryTreeForwardInorderIterator itr=(BinaryTreeForwardInorderIterator) avlBtRandom.forwardInorderIterator();
            		
                    result = TestRandomInsertAndDelete.testInsertAndDeleteAVL(avlBtRandom, 1, 25);
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

        // System.out.println(avlBtRandom.size());
        System.out.println("Your code passed all the tests.");
    }
}
