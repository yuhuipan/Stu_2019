package com.yuhui.juc.c_006_UnSafe;

import sun.misc.Unsafe;

public class HelloUnsafe {
    static class M {
        private M() {
        }

        int i;
    }

    public static void main(String[] args) throws InstantiationException {
        Unsafe unsafe = Unsafe.getUnsafe();
        M m = (M) unsafe.allocateInstance(M.class);
        m.i = 9;
        System.out.println(m.i);
    }
}
