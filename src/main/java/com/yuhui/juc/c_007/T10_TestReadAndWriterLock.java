package com.yuhui.juc.c_007;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T10_TestReadAndWriterLock {
    private static int value;
    // static Lock lock = new ReentrantLock();
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    private static void read(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over!"); //模拟读取操作
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void write(Lock lock, int v) {
        try {
            lock.lock();
            TimeUnit.SECONDS.sleep(1);
            value = v;
            System.out.println("write over!"); // 模拟写操作
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable readRun = () -> read(readLock);
        Runnable writeRun = () -> write(writeLock, new Random().nextInt());

        for (int i = 0; i < 10; i++) {
            new Thread(writeRun).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(readRun).start();
        }
    }
}
