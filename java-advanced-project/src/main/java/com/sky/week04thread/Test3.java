package com.sky.week04thread;

import com.sky.util.MathUtil;

import java.util.concurrent.CountDownLatch;

public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        final int sum = 2;
        CountDownLatch count = new CountDownLatch(sum);
        for (int i = 0; i < sum; i++) {
            int finalI = i+1;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long start=System.currentTimeMillis();
                    System.out.println(Thread.currentThread().getName()+ " fibo("+finalI*15+")=" + MathUtil.fibo(finalI*15) + " 使用时间："+ (System.currentTimeMillis()-start) + " ms");
                    count.countDown();
                }
            }).start();
        }
        System.out.println("等待"+sum+"个子线程执行完毕...");
        count.await();
        System.out.println(sum+"个子线程已经执行完毕");
        System.out.println("继续执行主线程");
    }
}
