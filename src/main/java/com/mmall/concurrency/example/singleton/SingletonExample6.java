package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@NotThreadSafe
public class SingletonExample6 {

    // 私有的构造函数
    private SingletonExample6() {

    }

    // 单例实例
    private static SingletonExample6 instance = null;

    // 静态的工厂方法
    public static SingletonExample6 getInstance() {
        if (instance == null) {
            instance = new SingletonExample6();
        }
        return instance;
    }
}
