package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierExample1 {

    private static CyclicBarrier cyclicBarrier= new CyclicBarrier(5);

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
        try {
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (Exception e){
            log.warn("BrokenBarrierException", e);
        }

        log.info("{} continue", threadNum);
    }
}
