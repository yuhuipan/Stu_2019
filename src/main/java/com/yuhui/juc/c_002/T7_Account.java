package com.yuhui.juc.c_002;

import java.util.concurrent.TimeUnit;

/**
 * 面试题：模拟银行账户
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 这样行不行？
 * <p>
 * 容易产生脏读问题（dirtyRead）
 */
public class T7_Account {
    private String name;
    private double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public /*synchronized*/ double getBalance(String name) {
        return balance;
    }

    public static void main(String[] args) {
        T7_Account account = new T7_Account();
        new Thread(() -> account.set("zs", 20)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance("zs"));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance("zs"));
    }
}
