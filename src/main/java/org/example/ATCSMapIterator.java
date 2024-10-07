package org.example;

import java.util.Iterator;

public interface ATCSMapIterator<K,V> extends Iterator<V> {
    boolean hasNext();
    V next() ;
}
