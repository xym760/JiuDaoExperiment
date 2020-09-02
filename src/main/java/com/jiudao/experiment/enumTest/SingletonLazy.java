package com.jiudao.experiment.enumTest;

/**
 * 懒汉式单例模式（适合多线程安全）
 */
public class SingletonLazy {
    private static volatile SingletonLazy instance;

    private SingletonLazy() {

    }

    /**
     * 由于synchronized的存在，效率很低，在单线程的情景下，完全可以去掉synchronized，为了兼顾效率与性能问题，需要改进
     *
     * @return
     */
    public static synchronized SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
