package org.example;

public interface ATCSSet<E> {
    void clear();
    boolean add(E obj);
    boolean contains(E obj);
    boolean isEmpty();
    int size();
    ATCSSetIterator<E> iterator();
}
