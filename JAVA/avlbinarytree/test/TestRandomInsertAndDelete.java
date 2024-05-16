
import java.util.Iterator;
import java.util.Random;

import ac.um.ds.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestRandomInsertAndDelete {
    private static int correctInsert[] = { 13, 99, 89, 15, 19, 4, 1, 3, 86, 60, 46, 78, 62, 98, 42, 66, 47, 11, 58, 6,
            44, 8, 9, 39, 48, 37, 57, 32, 2, 46 };
    private static int correctDelete[] = { 86, 66, 44, 57, 32 };

    public static boolean testInsertAndDeleteAVL(AVLBinaryTree<String, InternalAVLBinaryTreeNode<String>> avlBtStudent,
            int status, int size)
            throws IllegalAccessException {

        Random rand = new Random(9);
        boolean result = true;
        int bound = 100;
        int data = rand.nextInt(bound);
        String strData = String.valueOf(data);

        if (status == 0) {
            avlBtStudent.insertRootNode(strData);
            size--;
        }
        for (int i = 0; i < size; i++) {

            BinaryTreeForwardInorderIterator<String, InternalAVLBinaryTreeNode<String>> studentItrInOrder = (BinaryTreeForwardInorderIterator<String, InternalAVLBinaryTreeNode<String>>) avlBtStudent
                    .forwardInorderIterator();

            int step = rand.nextInt((avlBtStudent.size()));

            for (int j = 0; j < step; j++) {
                studentItrInOrder.next();
            }
            while (studentItrInOrder.getBinaryTreeNode().hasRightChild() &&
                    studentItrInOrder.getBinaryTreeNode().hasLeftChild()) {
                studentItrInOrder.next();

            }
            if (status == 0) {
                data = rand.nextInt(bound);
                strData = String.valueOf(data);
                int insertStatus = getInsertStatus(studentItrInOrder, rand);
                switch (insertStatus) {
                    case 0:
                    case 2:
                        avlBtStudent.insertLeftChild(studentItrInOrder.getBinaryTreeNode(), strData);
                        break;
                    case 1:
                    case 3:
                        avlBtStudent.insertRightChild(studentItrInOrder.getBinaryTreeNode(), strData);
                        break;
                }
            } else {
                int deleteStatus = getDeleteStatus(studentItrInOrder);
                switch (deleteStatus) {
                    case 0:
                        avlBtStudent.deleteLeftChild(studentItrInOrder.getBinaryTreeNode().getParent());
                        break;
                    case 1:
                        avlBtStudent.deleteRightChild(studentItrInOrder.getBinaryTreeNode().getParent());
                        break;
                }
            }

        }
        Iterator<String> studentItr = avlBtStudent.forwardInorderIterator();

        result = testIteration(studentItr, status);

        return result;
    }

    private static int getDeleteStatus(
            BinaryTreeForwardInorderIterator<String, InternalAVLBinaryTreeNode<String>> studentItrInOrder) {

        int status;

        BinaryTreeNode parentStudent = studentItrInOrder.getBinaryTreeNode().getParent();
        BinaryTreeNode nodeStudent = studentItrInOrder.getBinaryTreeNode();

        boolean deleteStatusStudent = nodeStudent.equals(parentStudent.getLeftChild());

        if (deleteStatusStudent) {
            status = 0;
        } else {
            status = 1;
        }

        return status;
    }

    private static int getInsertStatus(
            BinaryTreeForwardInorderIterator<String, InternalAVLBinaryTreeNode<String>> studentItrInOrder,
            Random rand) {
        int status;

        boolean insertStatusStudent = !studentItrInOrder.getBinaryTreeNode().hasLeftChild() &&
                !studentItrInOrder.getBinaryTreeNode().hasRightChild();

        if (insertStatusStudent) {
            // without child
            int leftOrRight = rand.nextInt(2);
            status = leftOrRight;
            return status;
        }

        insertStatusStudent = !studentItrInOrder.getBinaryTreeNode().hasLeftChild();

        if (insertStatusStudent) {
            // no left child
            status = 2;
            return status;
        }
        // no right child
        status = 3;

        return status;
    }

    private static boolean testIteration(Iterator<String> studentItr, int status) {
        boolean res = true;
        int i = 0;
        System.out.println("\n**Inorder Iteration:\nYours\tCorrect");
        while (studentItr.hasNext()) {
            String strStudent = studentItr.next();
            if (status == 0) {
                System.out.print(strStudent + "\t" + correctInsert[i]);
            } else {
                System.out.print(strStudent + "\t" + correctDelete[i]);
            }
            System.out.println();
            if (status == 0) {
                if (!strStudent.equals(String.valueOf(correctInsert[i])) && status == 0) {
                    res = false;
                    break;
                }
            } else {
                if (!strStudent.equals(String.valueOf(correctDelete[i])) && status == 1) {
                    res = false;
                  //  break;
                }
            }
            i++;
        }
         
        return res;
    }
}
