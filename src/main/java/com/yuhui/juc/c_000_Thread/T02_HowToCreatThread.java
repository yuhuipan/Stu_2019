package com.yuhui.juc.c_000_Thread;

/**
 * 请你告诉我启动线程的三种方式 1：Thread 2: Runnable 3:Executors.newCachedThread
 */
public class T02_HowToCreatThread {
    private static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("Hello MyThread!");
        }
    }

    private static class MyRun implements Runnable{
        @Override
        public void run() {
            System.out.println("Hello MyRun");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(()-> System.out.println("Hello Lambda！")).start();
    }
}
