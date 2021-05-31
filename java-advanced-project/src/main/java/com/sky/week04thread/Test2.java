package com.sky.week04thread;

import com.sky.util.MathUtil;

import java.util.concurrent.locks.LockSupport;

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + " fibo(15)=" + MathUtil.fibo(15) + " 使用时间：" + (System.currentTimeMillis() - start) + " ms");
            }
        });
        t1.start();
        System.out.println("等待子线程执行完毕...");
        t1.join();
        System.out.println("继续执行主线程");
    }
}
