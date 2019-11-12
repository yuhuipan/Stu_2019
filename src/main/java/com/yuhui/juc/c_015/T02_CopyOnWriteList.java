package com.yuhui.juc.c_015;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制容器 copy on write
 * 多线程环境下，写时效率低，读时效率高
 * 适合写少读多的环境
 */
public class T02_CopyOnWriteList {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        //List<String> list = new Vector<>();
        //List<String> list = new ArrayList<>(); // 这个会出并发问题！
        Random r = new Random();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    list.add("a" + r.nextInt(100000));
                }
            });
        }
        runAndComputeTime(threads);
        System.out.println(list.size());
    }

    private static void runAndComputeTime(Thread[] threads) {
        long start = System.currentTimeMillis();
        Arrays.asList(threads).forEach(Thread::start);
        Arrays.asList(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end -start);
    }
}
