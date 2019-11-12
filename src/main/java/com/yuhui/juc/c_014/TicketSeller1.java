package com.yuhui.juc.c_014;

import java.util.ArrayList;
import java.util.List;

public class TicketSeller1 {

    static List<String> ticks = new ArrayList<>();

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
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 销售了--" + ticks.remove(0));
                }
            }, "T" + i).start();
        }
    }
}
