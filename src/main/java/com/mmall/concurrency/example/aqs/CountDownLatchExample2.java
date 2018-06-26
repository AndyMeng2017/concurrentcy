package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2 {

    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch= new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;

            executorService.execute(()->{
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    log.error("exception", e);
                } finally {
                    countDownLatch.countDown();
                }

            });
        }
        //到了等待时间，代码就会往下执行，而已有的线程依然会继续执行
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        executorService.shutdown();
    }

    public static void test(int threadCount) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", threadCount);
    }
}
