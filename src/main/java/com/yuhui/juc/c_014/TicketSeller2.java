package com.yuhui.juc.c_014;

import java.util.Vector;

public class TicketSeller2 {
    static Vector<String> ticks = new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) {
            ticks.add("票NO" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (ticks.size() > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 销售了--" + ticks.remove(0));
                }
            }, "T" + i).start();
        }
    }
}
