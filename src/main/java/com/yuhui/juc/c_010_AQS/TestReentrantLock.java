package com.yuhui.juc.c_010_AQS;

import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {
    private static volatile int i = 0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        i++;
        lock.unlock();
    }
}
