package com.sky.testinsert;

import com.sky.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class Main1 {
    public static void main(String[] args) throws InterruptedException {
        // 插入数据总数
        int sum = 1000000;
        // 线程数
        int threadSum = 10;
        InsertByThead insert = new InsertByThead();
        CountDownLatch count = new CountDownLatch(threadSum);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threadSum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+"开始插入数据");
                        insert.insert(sum / threadSum,Thread.currentThread().getName());
                        System.out.println(Thread.currentThread().getName()+"插入"+(sum / threadSum)+"条数据完毕");
                        count.countDown();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }).start();
        }
        System.out.println("等待"+threadSum+"个子线程执行完毕...");
        count.await();
        DBUtils.close();
        long diff =  (System.currentTimeMillis() - start)/1000;
        System.out.println(threadSum+"个子线程插入完毕，用时：" + diff + "s");
    }
}
