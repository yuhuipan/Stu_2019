package com.yuhui.juc.c_007;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class T11_TestSemaphore {
    public static void main(String[] args) {
        //Semaphore semaphore = new Semaphore(2);
        //Semaphore semaphore = new Semaphore(2, true);
        //允许一个线程同时执行
        Semaphore semaphore = new Semaphore(1);
        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T1 running");
                Thread.sleep(200);
                System.out.println("T1 end....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();
        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T2 running");
                Thread.sleep(200);
                System.out.println("T2 end....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();
    }
}
