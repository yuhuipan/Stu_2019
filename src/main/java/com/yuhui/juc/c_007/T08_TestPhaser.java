package com.yuhui.juc.c_007;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class T08_TestPhaser {
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        phaser.bulkRegister(5);
        for (int i = 0; i < 5; i++) {
            final int nameIndex = i;
            new Thread(() -> {
                Person person = new Person("Person" + nameIndex);
                person.arrive();
                phaser.arriveAndAwaitAdvance();

                person.eat();
                phaser.arriveAndAwaitAdvance();

                person.leave();
                phaser.arriveAndAwaitAdvance();
            }).start();
        }
    }

    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人都到齐了！");
                    return false;
                case 1:
                    System.out.println("所有人都吃完了！");
                    return false;
                case 2:
                    System.out.println("所有人都离开了！");
                    System.out.println("婚礼结束！");
                    return true;
                default:
                    return true;
            }
        }
    }

    static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达现场！\n", name);
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完了！\n", name);
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开了！\n", name);
        }
    }
}
