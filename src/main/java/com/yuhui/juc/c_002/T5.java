package com.yuhui.juc.c_002;

/**
 *对比上面一个小程序，分析一下这个程序的输出
 */
public class T5 implements Runnable{
    private int count = 10;
    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        T5 t = new T5();
        for(int i=0; i<50; i++) {
            //T5 t = new T5();
            new Thread(t, "THREAD" + i).start();
        }
    }
}
