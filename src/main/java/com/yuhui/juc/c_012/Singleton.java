package com.yuhui.juc.c_012;

import java.util.Arrays;

public class Singleton {
    static {
        System.out.println("s");
    }

    private Singleton() {
        System.out.println("single");
    }

    private static class Inner {
        static {
            System.out.println("inner");
        }
        private static Singleton singleton = new Singleton();
    }

    private static Singleton getInstance() {
        return Inner.singleton;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[200];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()-> System.out.println(Singleton.getInstance()));
        }
        Arrays.asList(threads).forEach(Thread::start);
    }
}
