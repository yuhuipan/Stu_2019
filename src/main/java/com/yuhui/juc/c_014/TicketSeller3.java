package com.yuhui.juc.c_014;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TicketSeller3 {

    static List<String> tickets = new LinkedList<>();


    static {
        for(int i=0; i<1000; i++) tickets.add("票 编号：" + i);
    }

    public static void main(String[] args) {

        for(int i=0; i<10; i++) {
            new Thread(()->{
                while(true) {
                    synchronized(tickets) {
                        if(tickets.size() <= 0) {
                            System.out.println(Thread.currentThread().getName() + " break");
                            break;
                        }
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(Thread.currentThread().getName() + " 销售了--" + tickets.remove(0));
                    }
                }
            },"T" + i).start();
        }
    }
}
