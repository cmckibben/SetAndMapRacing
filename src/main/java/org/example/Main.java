package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;


public class Main {
    public static void main(String[] args) {
        ATCSSet<Long> set = new MySet<Long>();
        ATCSMap<Long,String> map = new MyMap<Long,String>();
        final int runs = 1;
        final int size = 100000;
        long[] unsortedArray = new long[size];
        for (int i = 0; i < size; i++)
            unsortedArray[i] = (long) ((Math.random() * Long.MAX_VALUE) + 1);

        long startTime = 0;
        long endTime = 0;
        ArrayList<Long> setOpsRunTimes = new ArrayList<Long>();
        ArrayList<Long> mapOpsRunTimes = new ArrayList<Long>();
        BigInteger counter = BigInteger.ZERO;
        BigInteger average = BigInteger.ZERO;

        for (int i = 0; i < runs; i++) {
            startTime = System.currentTimeMillis();
            for (int j = 0; j < size; j++)
                set.add(unsortedArray[j]);
            for (int j = 0; j < size; j++)
                set.contains(unsortedArray[j]);
            Iterator<Long> iterator = set.iterator();
            while (iterator.hasNext()) {
                iterator.next();
            }
            set.clear();
            endTime = System.currentTimeMillis();
            setOpsRunTimes.add((endTime - startTime));
        }
        counter = BigInteger.ZERO;
        for (Long value : setOpsRunTimes) {
            counter = counter.add(BigInteger.valueOf(value));
        }
        average = counter.divide(BigInteger.valueOf(runs));
        System.out.printf("\tSet operations took: %,15d milliseconds", average.longValue());

        for (int i = 0; i < runs; i++) {
            startTime = System.currentTimeMillis();
            for (int j = 0; j < size; j++)
                map.put(unsortedArray[j]," ");
            for (int j = 0; j < size; j++)
                map.containsKey(unsortedArray[j]);
            ATCSMapIterator<Long,String> iterator = map.keyIterator();
            while (iterator.hasNext()) {
                iterator.next();
            }
            map.clear();
            endTime = System.currentTimeMillis();
            mapOpsRunTimes.add((endTime - startTime));
        }
        counter = BigInteger.ZERO;
        for (Long value : mapOpsRunTimes) {
            counter = counter.add(BigInteger.valueOf(value));
        }
        average = counter.divide(BigInteger.valueOf(runs));
        System.out.printf("\n\tMap operations took: %,15d milliseconds\n", average.longValue());
    }
}