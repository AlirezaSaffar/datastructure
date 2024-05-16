import java.util.Random;

import ac.um.ds.ISort;
import ac.um.ds.SelectionSort;

public class Test {

    public static void testSorting() {
        int n = 10;
        ISort<Integer> sorter = new SelectionSort<>();
        Integer[] A = new Integer[n];

        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            A[i] = rand.nextInt(1000);
        }

        System.out.println("Initial array:");
        // System.out.println(Arrays.toString(A));
        for (int i = 0; i < n; i++) {
            System.out.print(A[i] + ", ");
        }

        sorter.Sort(A);

        System.out.println("\n\nSorted array:");
        // System.out.println(Arrays.toString(A));
        for (int i = 0; i < n; i++) {
            System.out.print(A[i] + ", ");
        }
    }

    public static void testTemplate() {
        int n = 20;
        ISort<X> sorter = new SelectionSort<>();
        X[] A = new X[n];

        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            A[i] = new X(rand.nextInt(1000));
        }

        System.out.println("Initial array:");
        // System.out.println(Arrays.toString(A));
        for (int i = 0; i < n; i++) {
            System.out.print(A[i].getVal() + ", ");
        }

        sorter.Sort(A);

        System.out.println("\n\nSorted array:");
        // System.out.println(Arrays.toString(A));
        for (int i = 0; i < n; i++) {
            System.out.print(A[i].getVal() + ", ");
        }
    }

    public static void main(String[] args) {
        System.out.println("Test 1. Sorting");
        testSorting();

        System.out.println("\n\n\n");
        System.out.println("---------------------------------------------");
        System.out.println("Test 2. Template");
        testTemplate();

        int dummy;
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        dummy = scanner.nextInt();
    }
}
