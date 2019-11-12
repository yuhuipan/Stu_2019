package com.yuhui.juc.c_000_Thread;

import java.util.concurrent.TimeUnit;

/**
 * NEW,
 * RUNNABLE,
 * BLOCKED,
 * WAITING,
 * TIMED_WAITING,
 * TERMINATED;
 */
public class T04_ThreadState {

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello MyThread!");
            System.out.println(this.getState());
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new MyThread();
        System.out.println(t.getState());
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t.getState());
    }
}
