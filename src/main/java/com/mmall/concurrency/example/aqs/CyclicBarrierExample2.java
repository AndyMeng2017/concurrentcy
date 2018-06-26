package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CyclicBarrierExample2 {

    private static CyclicBarrier cyclicBarrier= new CyclicBarrier(5,()->{
        log.info("callback is running");
    });

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);

            executorService.execute(()->{

                try {
                    rece(threadNum);
                } catch (Exception e) {
                    log.error("exception", e);
                }

            });
        }
        executorService.shutdown();
    }

    private static void rece(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} id ready.", threadNum);
        cyclicBarrier.await();
        log.info("{} continue", threadNum);
    }
}
