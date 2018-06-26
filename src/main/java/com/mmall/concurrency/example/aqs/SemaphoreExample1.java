package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample1 {

    private static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire(1); // 获取多个许可
                    test(threadNum);
                    semaphore.release(1); // 释放多个许可
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }

            });
        }
        log.info("finish");
        executorService.shutdown();
    }

    public static void test(int threadCount) throws InterruptedException {
        log.info("{}", threadCount);
        Thread.sleep(5000);
    }
}
