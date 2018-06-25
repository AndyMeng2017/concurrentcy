package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.Recommend;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 枚举模式，最安全的
 */
@Slf4j
@ThreadSafe
@Recommend
public class SingletonExample6 {

    // 私有的构造函数
    private SingletonExample6() {

    }

    public static SingletonExample6 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample6 singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonExample6();
        }

        public SingletonExample6 getInstance() {
            return singleton;
        }
    }


}
