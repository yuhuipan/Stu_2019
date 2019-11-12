package com.yuhui.juc.c_003_volatile;
/**
 * volatile 引用类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 */

import java.util.concurrent.TimeUnit;

public class T02_VolatileReference1 {
    boolean running = true;
    volatile static T02_VolatileReference1 t = new T02_VolatileReference1();

    void m() {
        System.out.println("m start...");
        while (running) {

        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {
        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
