package com.yuhui.juc.c_015;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class T07_DelayQueue {
    static class MyTask implements Delayed {

        long runningTime;

        public MyTask(long runningTime) {
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            long l = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
            if (l < 0) {
                return -1;
            }
            if (l > 0) {
                return 1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return runningTime + "";
        }
    }

    static BlockingQueue<MyTask> tasks = new DelayQueue<MyTask>();
    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask(now);
        MyTask t2 = new MyTask(now + 1000);
        MyTask t3 = new MyTask(now + 2000);
        MyTask t4 = new MyTask(now + 1500);
        MyTask t5 = new MyTask(now + 500);
        System.out.printf("[%s, %s, %s, %s, %s]\n",t1,t2,t3,t4,t5);
        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);
        System.out.println(tasks);
        for (int i = 0; i < 5; i++) {
            MyTask task = tasks.take();
            System.out.println(task); // t1 t5 t2 t4 t3
        }
    }
}
