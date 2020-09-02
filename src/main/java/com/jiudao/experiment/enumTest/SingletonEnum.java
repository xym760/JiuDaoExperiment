package com.jiudao.experiment.enumTest;

/**
 * 枚举单例
 *
 * 代码相当简洁，我们也可以像常规类一样编写enum类，为其添加变量和方法，
 * 访问方式也更简单，使用SingletonEnum.INSTANCE进行访问，这样也就避免调用getInstance方法，
 * 更重要的是使用枚举单例的写法，我们完全不用考虑序列化和反射的问题。枚举序列化是由jvm保证的，
 * 每一个枚举类型和定义的枚举变量在JVM中都是唯一的，在枚举类型的序列化和反序列化上，
 * Java做了特殊的规定：在序列化时Java仅仅是将枚举对象的name属性输出到结果中，
 * 反序列化的时候则是通过java.lang.Enum的valueOf方法来根据名字查找枚举对象。
 * 同时，编译器是不允许任何对这种序列化机制的定制的并禁用了
 * writeObject、readObject、readObjectNoData、writeReplace和readResolve等方法，从而保证了枚举实例的唯一性
 */
public enum SingletonEnum {
    INSTANCE;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
