package com.yuhui.juc.c_002;

/**
 * volatile
 * synchronized
 * Reports any non-atomic operation on volatile
 * 分析一下这个程序的输出
 */
public class T4 implements Runnable {
    private /*volatile*/ int count = 100;

    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count= " + count);
    }

    public static void main(String[] args) {
        T4 t = new T4();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "Thread" + i).start();
        }

    }
}
