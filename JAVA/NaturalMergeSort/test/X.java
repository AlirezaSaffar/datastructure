public class X implements Comparable<X> {
    private int value;

    public X(int n) {
        value = n;
    }

    public int getVal() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(X A) {
        if (value < A.getVal())
            return -1;
        else if (value > A.getVal())
            return 1;
        else
            return 0;
    }
}