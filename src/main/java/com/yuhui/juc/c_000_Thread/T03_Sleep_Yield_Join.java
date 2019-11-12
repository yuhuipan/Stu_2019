package com.yuhui.juc.c_000_Thread;

import java.util.concurrent.TimeUnit;

public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
        //testSleep();
        //testYield();
        testJoin();
    }

    private static void testSleep() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                if (i / 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
        new Thread(() -> {
            for (int j = 0; j < 100; j++) {
                System.out.println("-------B" + j);
                if (j / 10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
    }

    private static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                    //TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("B" + i);
                try {
                    Thread.sleep(500);
                    //TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t2.start();
        t1.start();
    }
}
