package org.example;

public interface ATCSMap<K,V> {
    void clear();
    boolean isEmpty();
    int size();
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean containsKey(K key);
    ATCSMapIterator<K,V> keyIterator();
}
