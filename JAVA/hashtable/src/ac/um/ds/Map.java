package ac.um.ds;

public interface Map< K extends Comparable<K> ,  V> {
    void assign(K key, V value);
    void remove(K key);
    boolean hasKey(K key);
    V get(K key);
    int size();
}

