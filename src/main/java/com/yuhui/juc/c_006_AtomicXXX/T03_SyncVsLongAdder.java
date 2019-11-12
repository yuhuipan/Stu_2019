package com.yuhui.juc.c_006_AtomicXXX;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class T03_SyncVsLongAdder {
    static int count2;
    static LongAdder count = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[500];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < 100000; k++) {
                    count.increment();
                }
            });
        }
        long start = System.currentTimeMillis();

        for (Thread t : threads) t.start();

        for (Thread t : threads) t.join();

        long end = System.currentTimeMillis();

        System.out.printf("Atomic: %s ,time: %s\n", count.longValue(), (end - start));

        Object lock = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                synchronized (lock) {
                    count2++;
                }
            });
        }

        start = System.currentTimeMillis();

        for (Thread t : threads) t.start();

        for (Thread t : threads) t.join();

        end = System.currentTimeMillis();

        System.out.printf("Atomic: %s ,time: %s\n", count.longValue(), (end - start));
    }

    static void microSleep(int m){
        try {
            TimeUnit.SECONDS.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
