package com.yuhui.juc.c_007;

import java.util.concurrent.Exchanger;

public class T12_TestExchanger {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String s = "T1";
            try {
                System.out.println("t1 before exchange");
                s = exchanger.exchange(s);
                System.out.println("t1 after exchange");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 " + Thread.currentThread().getName() + " " + s);
        }, "t1").start();

        new Thread(() -> {
            String s = "T2";
            try {
                System.out.println("t2 before exchange");
                s = exchanger.exchange(s);
                System.out.println("t2 after exchange");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 " + Thread.currentThread().getName() + " " + s);
        }, "t2").start();
    }
}
