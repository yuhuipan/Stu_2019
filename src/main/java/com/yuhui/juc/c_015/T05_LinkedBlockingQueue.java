package com.yuhui.juc.c_015;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class T05_LinkedBlockingQueue {

    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();

    static Random r = new Random();

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + " put a" + i);
                    strs.put("a" + i); // 如果满了，就会等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                while (true) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "take - " + strs.take()); // 如果空了，就会等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"c" + i).start();
        }
    }
}
