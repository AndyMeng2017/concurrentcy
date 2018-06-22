package com.mmall.concurrency.example.concurrency;

import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class ConcurrencyExample5 {

    private static AtomicIntegerFieldUpdater<ConcurrencyExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(ConcurrencyExample5.class, "count");

    @Getter
    public volatile int count = 100;


    public static void main(String[] args) {

        ConcurrencyExample5 example5 = new ConcurrencyExample5();
        if (updater.compareAndSet(example5, 100,120)) {
            log.info("update success1. {}", example5.getCount());
        }
        if (updater.compareAndSet(example5, 100,120)) {
            log.info("update success2. {}", example5.getCount());
        } else {
            log.info("update failded. {}", example5.getCount());
        }

    }


}
