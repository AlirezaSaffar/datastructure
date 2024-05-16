import ac.um.ds.CountingSort;

public class CountingSortTest {
	final static int testSize = 10;

    public static void main(String[] args) {
		short k = 25;
	    Student[] data = new Student[testSize];
    	Student[] correctResult = new Student[testSize];
    	CountingSort<Student> sorter = new CountingSort<Student>(k);

        System.out.println("\n** Initialized array:");
        data = getData();
        printData(data);

        sorter.sort(data);

        System.out.println("\n\n** Sorted array:");
        printData(data);

        System.out.println("\n\n** Expected Results:");
        correctResult = getCorrectResult();
        printData(correctResult);

        for (int i = 0; i < testSize; i++) {
            if (data[i].getKey() != correctResult[i].getKey()){
                System.out.println();
                throw new RuntimeException("Incorrect Sort");
            }
        }
        for (int i = 0; i < testSize; i++) {
            if (data[i].getId() != correctResult[i].getId()){
                System.out.println();
                throw new RuntimeException("Unstable Sort");
            }
        }

        System.out.println("\n\n");
    }

	private static Student[] getData() {
		int[] ages = {20, 19, 19, 22, 20, 24, 19, 21, 24, 25};
		String[] names = {"John", "Jane", "Mike", "Emma", "Alex",
						 "Lisa", "Alex", "Alice", "David", "Sophia"};
		int[] studentId = {1001, 1002, 1003, 1004, 1005,
							 1006, 1007, 1008, 1009, 1010};

		Student[] data = new Student[testSize];

		for (int i = 0; i < testSize; i++) {
			Student student = new Student(studentId[i], names[i], ages[i]);
			data[i] = student;
		}    

		return data;
	}


    private static Student[] getCorrectResult() {
        int[] ages = {19, 19, 19, 20, 20, 21, 22, 24, 24, 25};
        String[] names = {"Jane", "Mike", "Alex", "John", "Alex",
							 "Alice", "Emma", "Lisa", "David", "Sophia"};
		int[] studentId = {1002, 1003, 1007, 1001, 1005,
							 1008, 1004, 1006, 1009, 1010};
		Student[] expectedRes = new Student[testSize];

		for (int i = 0; i < testSize; i++) {
			Student student = new Student(studentId[i], names[i], ages[i]);
			expectedRes[i] = student;
		}    

		return expectedRes;
    }

    private static void printData(Student[] data){
        for (int i = 0; i < testSize; i++) {
            System.out.print("(" + data[i].getKey() + ", " + data[i].getName() +
                    ", id:" + data[i].getId() + "), ");
        }
    }
}
