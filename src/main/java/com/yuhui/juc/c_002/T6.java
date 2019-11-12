package com.yuhui.juc.c_002;

import java.util.concurrent.TimeUnit;

/**
 * 同步和非同步方法是否可以同时调用？
 */
public class T6 {
    public synchronized void m1() {
        System.out.println("m1 start...");
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 end");
    }

    public void m2() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end");
    }

    public static void main(String[] args) {
        T6 t = new T6();
        /*new Thread(() -> t.m1(), "t1").start();
        new Thread(() -> t.m2(), "t2").start();*/
        new Thread(t::m1, "t1").start(); //method reference
        new Thread(t::m2, "t2").start();
    }
}
