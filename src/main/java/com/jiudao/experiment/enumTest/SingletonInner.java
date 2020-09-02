package com.jiudao.experiment.enumTest;

/**
 * 静态内部类单例模式
 */
public class SingletonInner {
    private static class Holder {
        //避免了静态实例在SingletonInner类的加载阶段就创建对象,并且由于静态内部类只会被加载一次，所以这种写法也是线程安全的。
        private static SingletonInner singleton = new SingletonInner();
    }

    private SingletonInner() {

    }

    public static SingletonInner getInstance() {
        return Holder.singleton;
    }
}
