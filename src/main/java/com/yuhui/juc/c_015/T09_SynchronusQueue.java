package com.yuhui.juc.c_015;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T09_SynchronusQueue {
    public static void main(String[] args) throws InterruptedException { //容量为0
        BlockingQueue<String> strings = new SynchronousQueue<String>();
        /*new Thread(()->{
            try {
                strings.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
        strings.put("aaa"); //阻塞等待消费者消费
        //strings.add("aaa"); //ava.lang.IllegalStateException: Queue full
        System.out.println(strings.size());
    }
}
