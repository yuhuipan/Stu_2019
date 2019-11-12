package com.yuhui.juc.c_015;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {

            queue.put("a" + i);
        }
        System.out.println(queue);
        // queue.add("bbb"); // java.lang.IllegalStateException: Queue full
        System.out.println(queue.offer("aaa"));
        queue.offer("aaa",5, TimeUnit.SECONDS);
        System.out.println(queue);
        queue.put("aaa"); // 满了就会等待，程序阻塞
        System.out.println(queue);
    }
}
