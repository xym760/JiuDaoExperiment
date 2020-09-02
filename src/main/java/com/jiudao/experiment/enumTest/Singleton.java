package com.jiudao.experiment.enumTest;

import java.io.Serializable;

/**
 * 改进后的懒汉式单例
 * 还应该注意到如果单例类维持了其他对象的状态时还需要使他们成为transient的对象，
 * 这种就更复杂了。终极方案是用枚举单例。
 */
public class Singleton implements Serializable {
    private volatile static Singleton singleton = null;
    private static volatile boolean flag = true;

    /**
     * 使用反射强行调用私有构造器，解决方式可以修改构造器，让它在创建第二个实例的时候抛异常
     */
    private Singleton() {
        if (flag) {
            flag = false;
        } else {
            throw new RuntimeException("The instance already exists!");
        }
    }

    /**
     * 双重检查锁
     *
     * @return
     */
    public static Singleton getInstance() {
        if (singleton == null) {//如果为空，才创建，否则直接返回单例对象，用于优化性能
            synchronized (Singleton.class) {//因为当前没有对象，只能加类锁，又因为可能多个线程进入该语句处，让这些线程排队执行下面的代码
                if (singleton == null) {//防止线性排队进来的多个线程创建多个实例，所以加判断
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    /**
     * 每次反序列化一个序列化的对象实例时都会创建一个新的实例,
     * 解决方法是反序列时直接返回当前INSTANCE。
     * @return
     */
    private Object readResolve() {
        return singleton;
    }
}
