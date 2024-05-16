import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Arrays;
import ac.um.ds.ISort;
import ac.um.ds.NaturalMergeSort;

public class Test {

    public static void main(String[] args) {

        System.out.println("Test 1. Merge\n");
        testMerge();
        System.out.println("\n\n");
        System.out.println("---------------------------------------------");
        System.out.println("Test 2. Borders\n");
        testBorders();
        System.out.println("\n\n");
        System.out.println("---------------------------------------------");
        System.out.println("Test 3. Sorting\n");
        testSorting();
        System.out.println("\n\n");
        System.out.println("---------------------------------------------");
        System.out.println("Test 4. Template\n");
        testTemplate();

        int dummy;
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        dummy = scanner.nextInt();
        scanner.close();
    }
    
    public static void testSorting() {
        int n = 10;
        ISort<Integer> sorter = new NaturalMergeSort();
        Integer[] A = new Integer[n];

        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            A[i] = rand.nextInt(1000);
        }

        System.out.println("Initial array:");
        for (int i = 0; i < n; i++) {
            System.out.print(A[i] + ", ");
        }

        sorter.sort(A, n);

        System.out.println("\n\nSorted array:");
        for (int i = 0; i < n; i++) {
            System.out.print(A[i] + ", ");
        }

        if (!isSorted(A)) {
            System.out.println();
            throw new RuntimeException("Array is not sorted");
        }

    }

    public static void testBorders() {
        NaturalMergeSort<Integer> sorter = new NaturalMergeSort<Integer>();
        Integer[] A = {2, 3, 3, 6, 4, 8, 9, 9, 2, 9, 8, 7, 1, 8, 9};
        Integer[] B = {0, 4, 8, 10, 11, 12, 15};

        System.out.println("Initial array:");
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + ", ");
        }

        System.out.println("\n\nCorrect border indexes:");
        for (int i = 0; i < B.length; i++) {
            System.out.print(B[i] + ", ");
        }

        Queue<Integer> indexes = new LinkedList<Integer>();
        sorter.findBorders(A, A.length, indexes);

        System.out.println("\n\nFound border indexes:");
        for (Integer index : indexes) {
            System.out.print(index + ", ");
         }

         Queue<Integer> BQueue = new LinkedList<Integer>(Arrays.asList(B));

        if (!BQueue.equals(indexes)) {
            System.out.println();
            throw new RuntimeException("Incorrect Borders");
        }
    }

    public static void testMerge(){

        NaturalMergeSort<Integer> sorter = new NaturalMergeSort<Integer>();
        Integer[] A = {4, 1, 3, 2, 3, 3, 4, 5, 8, 1, 2, 3, 4};
        Integer[] correctResult = {4, 1, 3, 1, 2, 2, 3, 3, 3, 4, 4, 5, 8};

        int r = 13;
        int p = 3;
        int q = 9;

        System.out.println("Initial array:");
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + ", ");
        }

        System.out.println("\n\nCorrect Merge:");
        for (int i = 0; i < A.length; i++) {
            System.out.print(correctResult[i] + ", ");
        }

        sorter.merge(A, p, q, r);

        System.out.println("\n\nMerged output:");
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + ", ");
        }

        for (int i = 0; i < A.length; i++) {
            if(A[i] != correctResult[i]){
                System.out.println();
                throw new RuntimeException("Incorrect Merge");
            }
        }

    }

    public static void testTemplate() {
        int n = 20;
        try{
            ISort<X> sorter = new NaturalMergeSort<X>();
            X[] A = new X[n];

            Random rand = new Random();
            for (int i = 0; i < n; i++) {
                A[i] = new X(rand.nextInt(1000));
            }

            X[] B = A.clone();

            System.out.println("Initial array:");
            for (int i = 0; i < n; i++) {
                System.out.print(A[i].getVal() + ", ");
            }

            sorter.sort(A, n);
            Arrays.sort(B);

            System.out.println("\n\nSorted array:");
            for (int i = 0; i < n; i++) {
                System.out.print(A[i].getVal() + ", ");
            }

            for (int i = 0; i < n; i++) {
                if(A[i].getVal() != B[i].getVal())
                    throw new RuntimeException();
            }

        }catch(Exception e){
            System.out.println("\nYou are not using template for variables!");
        }

    }

    private static boolean isSorted(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

}
