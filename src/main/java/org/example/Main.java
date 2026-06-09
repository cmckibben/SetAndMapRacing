package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;


public class Main {
    public static void main(String[] args) throws IOException {
        boolean debug = true;
        int runs = 5;
        int size = 100000;
        if(debug) {
           runs = 2;
           size  = 1001;
        }

        ATCSSet<Long> set = new MySet<>();
        ATCSMap<Long,String> map = new MyMap<>();


        long[] unsortedArray = new long[size];
        for (int i = 0; i < size; i++)
            unsortedArray[i] = (long) ((Math.random() * Long.MAX_VALUE) + 1);

        long startTime = 0;
        long endTime = 0;
        ArrayList<Long> setOpsRunTimes = new ArrayList<Long>();
        ArrayList<Long> mapOpsRunTimes = new ArrayList<Long>();
        BigInteger counter = BigInteger.ZERO;
        BigInteger average_set = BigInteger.ZERO;
        BigInteger average_map = BigInteger.ZERO;
        long average_all;

        for (int i = 0; i < runs; i++) {
            startTime = System.nanoTime();
            for (int j = 0; j < size; j++)
                set.add(unsortedArray[j]);
            for (int j = 0; j < size; j++)
                set.contains(unsortedArray[j]);
            Iterator<Long> iterator = set.iterator();
            while (iterator.hasNext()) {
                iterator.next();
            }
            set.clear();
            endTime = System.nanoTime();
            setOpsRunTimes.add((endTime - startTime));
        }
        for (Long value : setOpsRunTimes) {
            counter = counter.add(BigInteger.valueOf(value));
        }
        average_set = counter.divide(BigInteger.valueOf(runs));
        System.out.printf("\tSet operations took: %,15d nanoseconds", average_set.longValue());

        for (int i = 0; i < runs; i++) {
            startTime = System.nanoTime();
            for (int j = 0; j < size; j++)
                map.put(unsortedArray[j]," ");
            for (int j = 0; j < size; j++)
                map.containsKey(unsortedArray[j]);
            ATCSMapIterator<Long,String> iterator = map.keyIterator();
            while (iterator.hasNext()) {
                iterator.next();
            }
            map.clear();
            endTime = System.nanoTime();
            mapOpsRunTimes.add((endTime - startTime));
        }
        counter = BigInteger.ZERO;
        for (Long value : mapOpsRunTimes) {
            counter = counter.add(BigInteger.valueOf(value));
        }
        average_map = counter.divide(BigInteger.valueOf(runs));
        average_all = (average_map.longValue()+average_set.longValue())/2;
        System.out.printf("\n\tMap operations took: %,15d nanoseconds\n", average_map.longValue());
        System.out.printf("\n\tAll operations took: %,15d nanoseconds\n", average_all);
        FileWriter fw = new FileWriter(args[1], true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(String.format("\"%s\",%d,%d,%d\n",args[0], average_set.longValue(),average_map.longValue(),average_all));
        //bw.newLine();
        bw.close();
        //System.out.printf("\"%s\",%15d,%15d,%15d\n",args[0], average_set.longValue(),average_map.longValue(),average_all );
    }
}
