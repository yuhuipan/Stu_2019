package com.yuhui.juc.c_015;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * http://blog.csdn.net/sunxianghuang/article/details/52221913
 * http://www.educity.cn/java/498061.html
 * 阅读concurrentskiplistmap
 */
public class T01_ConcurrentMap {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        //Map<String, String> map = new ConcurrentSkipListMap<>(); //高并发并且排序
        Random random = new Random();
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    map.put("a" + random.nextInt(100000), "a" + random.nextInt(100000));
                    latch.countDown();
                }
            });
        }

        Arrays.asList(threads).forEach(Thread::start);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
