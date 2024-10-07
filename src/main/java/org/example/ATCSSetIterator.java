package org.example;

import java.util.Iterator;

public interface ATCSSetIterator<E> extends Iterator<E> {
    boolean hasNext();
    E next();
}