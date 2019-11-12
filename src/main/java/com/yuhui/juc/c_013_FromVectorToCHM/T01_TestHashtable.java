package com.yuhui.juc.c_013_FromVectorToCHM;

import java.util.Hashtable;
import java.util.UUID;

public class T01_TestHashtable {

    static Hashtable<UUID, UUID> m = new Hashtable<>();

    static int count = Constants.COUNT;

    static UUID[] keys = new UUID[count];

    static UUID[] values = new UUID[count];

    static final int THREAD_COUNT = Constants.THREAD_COUNT;

    // 初始化UUID[] keys values
    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {

        int start;

        // gap = 100
        int gap = count / THREAD_COUNT;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            // 给HashTable存入数据
            for (int i = start; i < start + gap; i++) {
                m.put(keys[i], values[i]);
            }
        }

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 100个线程
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < threads.length; i++) {
            // gap = count / THREAD_COUNT
            threads[i] = new MyThread(i * (count / THREAD_COUNT));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        System.out.println(m.size());

        //-----------------------------------

        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                // get(keys[10]) 一千万次
                for (int j = 0; j < 10000000; j++) {
                    m.get(keys[10]);
                }
            });
        }

        for(Thread t : threads) {
            t.start();
        }

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
