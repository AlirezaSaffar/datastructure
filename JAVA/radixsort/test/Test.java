
import ac.um.ds.RadixSort;
import ac.um.ds.UnboundedInteger;

public class Test {
    public static void main(String[] args) {

        System.out.println("=============================================");
        System.out.println("Test 1. calculateD");
        System.out.println("=============================================");
        testCalculateD();
        System.out.println("\n\n");
        System.out.println("=============================================");
        System.out.println("Test 2. getDigit");
        System.out.println("=============================================");
        testGetDigit();
        System.out.println("\n");
        System.out.println("=============================================");
        System.out.println("Test 3. CountingSort");
        System.out.println("=============================================");
        testCountingSort();
        System.out.println("\n\n");
        System.out.println("=============================================");
        System.out.println("Test 4. Sort");
        System.out.println("=============================================");
        testSort();

    }

    public static void testCalculateD() {
        RadixSort<Data> radixSort = new RadixSort<Data>();
        int maxR = 4;
        Data[] data = getData1();

        System.out.println("\nInitial array:");
        for (int i = 0; i < data.length; i++)
            System.out.println(data[i] + " ,");

        int[] correctRes = { 25, 13, 9, 7 };
        int[] studentRes = new int[maxR];

        System.out.println("\nCalculated maxDigit for 1 <= r <= 4:");
        for (int i = 0; i < maxR; i++) {
            int res = radixSort.calculateD(data, i + 1);
            studentRes[i] = res;
            System.out.print(res + " ,");
        }

        System.out.println("\n\nExpected results:");
        for (int i = 0; i < maxR; i++) {
            System.out.print(correctRes[i] + " ,");
        }

        for (int i = 0; i < maxR; i++) {
            if (studentRes[i] != correctRes[i]) {
                System.out.println();
                throw new RuntimeException("Incorrect calculateD function.");
            }
        }
    }

    public static void testGetDigit() {
        Data[] data = getData1();
        int[][] correctRes = {
                { 902, 77136, 86380, 80238, 86929, 22, 7, 24079, 82974, 86380 },
                { 0, 0, 0, 9851, 0, 0, 0, 69740, 99618, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 69125, 0, 0 }
        };
        int[][] studentRes = new int[3][data.length];
        int[] neededDigit = { 1, 3, 5 };
        int resNumbr = 0;
        int r = 5;

        System.out.println("\nInitial array:");
        for (int i = 0; i < data.length; i++)
            System.out.println(data[i] + " ,");

        for (int j : neededDigit) {
            System.out.println("\nCalculated digit for j = " + j + ":");
            for (int i = 0; i < data.length; i++) {
                int digit = data[i].getKey().getDigit(r, j);
                studentRes[resNumbr][i] = digit;
                System.out.print(digit + " ,");
            }

            System.out.println("\nExpected digit for j = " + j + ":");
            for (int i = 0; i < data.length; i++) {
                System.out.print(correctRes[resNumbr][i] + " ,");
            }
            System.out.println();
            resNumbr++;
        }

        for (int i = 0; i < resNumbr; i++) {
            for (int j = 0; j < data.length; j++) {
                if (studentRes[i][j] != correctRes[i][j]) {
                    System.out.println();
                    throw new RuntimeException("Incorrect getDigit function.");
                }
            }
        }
    }

    public static void testCountingSort() {
        Data[] data = getData2();
        int[] expectedKeys = { 2, 7, 2, 2, 10, 12, 10, 30, 30, 44 };
        int[] expectedValues = { 14, 16, 18, 19, 10, 11, 17, 12, 13, 15 };
        RadixSort<Data> radixSort = new RadixSort<Data>();

        System.out.println("Initial array:");
        for (int i = 0; i < data.length; i++)
            System.out.print(data[i] + " ,");

        int r = 1;
        int k = 10;
        int j = 2;

        radixSort.countingSort(data, k, r, j);

        System.out.println("\nCounting sort result:");
        for (int i = 0; i < data.length; i++)
            System.out.print(data[i] + " ,");

        System.out.println("\nExpected results:");
        for (int i = 0; i < data.length; i++)
            System.out.print("(" + expectedKeys[i] + " , " + expectedValues[i] + ") ,");

        for (int i = 0; i < data.length; i++) {
            int key = Integer.parseInt(data[i].getKey().getVal());
            if (expectedKeys[i] != key ||
                    expectedValues[i] != data[i].getValue()) {
                System.out.println();
                throw new RuntimeException("Incorrect sort.");
            }
        }

    }

    public static void testSort() {
        Data[] data = getData1();
        String[] expectedKeys = { "7", "22", "902", "86380", "86380",
                "86929", "93677136", "22749996187679482974",
                "91828098514531380238", "6912507057697400202524079" };
        int[] expectedValues = { 87, 92, 98, 79, 83, 33, 63, 62, 92, 66 };
        RadixSort<Data> radixSort = new RadixSort<Data>();

        System.out.println("Initial array:");
        for (int i = 0; i < data.length; i++)
            System.out.println(data[i] + " ,");

        radixSort.sort(data);

        System.out.println("\nSorted array:");
        for (int i = 0; i < data.length; i++)
            System.out.println(data[i] + " ,");

        System.out.println("\nExpected results:");
        for (int i = 0; i < data.length; i++)
            System.out.println("(" + expectedKeys[i] + " , " + expectedValues[i] + ") ,");

        for (int i = 0; i < data.length; i++) {
            String key = data[i].getKey().getVal();
            if (expectedKeys[i] != key) {
                System.out.println();
                throw new RuntimeException("Incorrect sort.");
            }
        }

        for (int i = 0; i < data.length; i++) {
            if (expectedValues[i] != data[i].getValue()) {
                System.out.println();
                throw new RuntimeException("Unstable sort.");
            }
        }
    }

    public static Data[] getData1() {
        int n = 10;
        Data[] data = new Data[n];
        data[0] = new Data(new UnboundedInteger("902"), 98);
        data[1] = new Data(new UnboundedInteger("93677136"), 63);
        data[2] = new Data(new UnboundedInteger("86380"), 79);
        data[3] = new Data(new UnboundedInteger("91828098514531380238"), 92);
        data[4] = new Data(new UnboundedInteger("86929"), 33);
        data[5] = new Data(new UnboundedInteger("22"), 92);
        data[6] = new Data(new UnboundedInteger("7"), 87);
        data[7] = new Data(new UnboundedInteger("6912507057697400202524079"), 66);
        data[8] = new Data(new UnboundedInteger("22749996187679482974"), 62);
        data[9] = new Data(new UnboundedInteger("86380"), 83);
        return data;
    }

    public static Data[] getData2() {
        int n = 10;
        Data[] data = new Data[n];
        data[0] = new Data(new UnboundedInteger("10"), 10);
        data[1] = new Data(new UnboundedInteger("12"), 11);
        data[2] = new Data(new UnboundedInteger("30"), 12);
        data[3] = new Data(new UnboundedInteger("30"), 13);
        data[4] = new Data(new UnboundedInteger("2"), 14);
        data[5] = new Data(new UnboundedInteger("44"), 15);
        data[6] = new Data(new UnboundedInteger("7"), 16);
        data[7] = new Data(new UnboundedInteger("10"), 17);
        data[8] = new Data(new UnboundedInteger("2"), 18);
        data[9] = new Data(new UnboundedInteger("2"), 19);
        return data;
    }
}