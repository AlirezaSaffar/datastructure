import ac.um.ds.LinearList;
import ac.um.ds.LinkedLinearList;

public class TestLinkedLinearList {

    public static void main(String[] args) throws Exception {

        System.out.println("=============================================");
        System.out.println("Test 1. Forward Insert/Remove");
        System.out.println("=============================================");
        testForwardInsertRemove();
        System.out.println("=============================================");
        System.out.println("Test 2. Backward Insert/Remove");
        System.out.println("=============================================");
        testBackwardInsertRemove();
        System.out.println("=============================================");
        System.out.println("Test 3. Iteration");
        System.out.println("=============================================");
        testIteration();
    }

    public static void testForwardInsertRemove() throws Exception {
        LinkedLinearList<Integer> lll = new LinkedLinearList<Integer>();

        int N = 10;
        int[][] expectedInsertRes = {
            {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
            {0, 1, 10, 2, 3, 4, 5, 6, 7, 8, 9}};
        int[] expectedRemoveRes = {0, 10, 2, 4, 5, 7, 8};

        LinearList.LinearListForwardIterator<Integer> itr = lll.forwardIterator();
        for (int i = 0; i < N; i++) {
            itr = lll.insert(itr, (Integer) i);
			itr.next();
        }
        System.out.println("Insert output 1:");
        System.out.println(lll);
        System.out.println("Expected output");
        System.out.println(" 0 1 2 3 4 5 6 7 8 9\n");

        itr = lll.forwardIterator();
        for (int i = 0; i < N; i++) {
            if(itr.next() != expectedInsertRes[0][i]){
                System.out.println();
                throw new RuntimeException("Incorrect insert function.");
            }
        }

        itr = lll.forwardIterator();
        itr.next();
        itr.next();
        itr = lll.insert(itr, 10);

        System.out.println("Insert output 2:");
        System.out.println(lll);
        System.out.println("Expected output");
        System.out.println(" 0 1 10 2 3 4 5 6 7 8 9\n");

        itr = lll.forwardIterator();
        for (int i = 0; i < N+1; i++) {
            if(itr.next() != expectedInsertRes[1][i]){
                System.out.println();
                throw new RuntimeException("Incorrect insert function.");
            }
        }
        
        //insert third check
        LinearList.LinearListBackwardIterator<Integer> itrBwd = lll.backwardIterator();
        for (int i = N; i >= 0 ; i--) {
            if(itrBwd.next() != expectedInsertRes[1][i]){
                System.out.println();
                throw new RuntimeException("Incorrect insert function.");
            }
        }

        itr = lll.forwardIterator();
        for (int i = 0; i < N; i += 3) {
            itr.next();
            itr.next();
            itr = lll.remove(itr);
        }

        System.out.println("Remove output 2:");
        System.out.println(lll);
        System.out.println("Expected output");
        System.out.println(" 0 10 2 4 5 7 8\n");

        itr = lll.forwardIterator();
        for (int i = 0; i <= 6; i++) {
            if(itr.next() != expectedRemoveRes[i]){
                System.out.println();
                throw new RuntimeException("Incorrect remove function.");
            }
        }
    }
 
    public static void testBackwardInsertRemove() throws Exception {
        LinkedLinearList<Integer> lll = new LinkedLinearList<Integer>();

        int N = 10;
        int[][] expectedInsertRes = {
            {9, 8, 7, 6, 5, 4, 3, 2, 1, 0},
            {9, 8, 7, 6, 5, 4, 3, 2, 10, 1, 0}};
        int[] expectedRemoveRes = {8, 7, 5, 4, 2, 10, 0};

        LinearList.LinearListBackwardIterator<Integer> itr = lll.backwardIterator();
        for (int i = 0; i < N; i++) {
            itr = lll.insert(itr, (Integer) i);
			itr.next();
        }
        System.out.println("Insert output 1:");
        System.out.println(lll);
        System.out.println("Expected output");
        System.out.println(" 9 8 7 6 5 4 3 2 1 0\n");

        itr = lll.backwardIterator();
        for (int i = N-1; i >= 0; i--) {
            if(itr.next() != expectedInsertRes[0][i]){
                System.out.println();
                throw new RuntimeException("Incorrect insert function.");
            }
        }

        itr = lll.backwardIterator();
        itr.next();
        itr.next();
        itr = lll.insert(itr, 10);

        System.out.println("Insert output 2:");
        System.out.println(lll);
        System.out.println("Expected output");
        System.out.println(" 9 8 7 6 5 4 3 2 10 1 0\n");

        itr = lll.backwardIterator();
        for (int i = N; i >= 0; i--) {
            if(itr.next() != expectedInsertRes[1][i]){
                System.out.println();
                throw new RuntimeException("Incorrect insert function.");
            }
        }
        
        //insert third check
        LinearList.LinearListForwardIterator<Integer> itrFwd = lll.forwardIterator();
        for (int i = 0; i <= N ; i++) {
            if(itrFwd.next() != expectedInsertRes[1][i]){
                System.out.println();
                throw new RuntimeException("Incorrect insert function.");
            }
        }

        itr = lll.backwardIterator();
        for (int i = 0; i < N; i += 3) {
            itr.next();
            itr.next();
            itr = lll.remove(itr);
        }

        System.out.println("Remove output 2:");
        System.out.println(lll);
        System.out.println("Expected output");
        System.out.println(" 8 7 5 4 2 10 0\n");

        itr = lll.backwardIterator();
        for (int i = 6; i >= 0; i--) {
            if(itr.next() != expectedRemoveRes[i]){
                System.out.println();
                throw new RuntimeException("Incorrect remove function.");
            }
        }
    }

    public static void testIteration() throws Exception {
        LinkedLinearList<Integer> lll = new LinkedLinearList<Integer>();
        LinearList.LinearListForwardIterator<Integer> itrFwd;
        LinearList.LinearListBackwardIterator<Integer> itrBckwd;

        LinearList.LinearListForwardIterator<Integer> it = lll.forwardIterator();
        for (int i = 0; i < 5; i++) {
            it = lll.insert(it, (Integer) i);
			it.next(); 
        }

        System.out.println("Initial list:");
        System.out.println("0 1 2 3 4 \n");

        System.out.println("Forward Iteration:");
        itrFwd = lll.forwardIterator();
        while (itrFwd.hasNext())
		{
			System.out.print(itrFwd.next());
			System.out.print(" ");
		}
        System.out.println();
        System.out.println("Expected Output:");
        System.out.println("0 1 2 3 4\n");

        System.out.println("Backward Iteration:");
        itrBckwd = lll.backwardIterator();
		while (itrBckwd.hasNext())
		{
			System.out.print(itrBckwd.next());
			System.out.print(" ");
		}
        System.out.println();
        System.out.println("Expected Output:");
        System.out.println("4 3 2 1 0\n");
    
    }
}
