package com.yuhui.juc.c_001_synchronized;

public class T3 {
    private static int count = 10;

    public static synchronized void m() { //这里等同于synchronized(T3.class)
        count--;
        System.out.println(Thread.currentThread().getName() + " count= " + count);
    }

    public static void mm() {
        synchronized (T3.class) { //考虑一下这里写synchronized(this)是否可以？
            count--;
            System.out.println(Thread.currentThread().getName() + " count= " + count);
        }
    }

}
