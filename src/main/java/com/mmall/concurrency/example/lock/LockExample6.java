package com.mmall.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(()->{
           try {
               reentrantLock.lock();
               log.info("wait signal"); //1
               condition.await();
           } catch (Exception e) {
               e.printStackTrace();
           }
           log.info("get signal"); // 3
           reentrantLock.unlock();
        }).start();

        new Thread(()->{
            try {
                reentrantLock.lock();
                log.info("get lock"); // 2
                Thread.sleep(3000);
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("send signal"); // 4
            reentrantLock.unlock();
        }).start();
    }

}

