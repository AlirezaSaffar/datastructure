package ac.um.ds;

public interface ISort<T extends Comparable<T>> {
    public void sort(T[] A, int n);
}
