package com.jiudao.experiment.enumTest;

interface Light {
    void blink();
}

interface Liquid {
    enum Drinks implements Liquid {
        COCA_COLA, WAHAHA;
    }

    enum Liquor implements Liquid {
        MAOTAI, JIANGXIAOBAI;
    }
}

/**
 * 如果打算在enum类中定义方法，务必在声明完枚举实例后使用分号分开，
 * 倘若在枚举实例前定义任何方法，编译器都将会报错，无法编译通过，
 * 同时即使自定义了构造函数且enum的定义结束，我们也永远无法手动调用构造函数创建枚举实例，毕竟这事只能由编译器执行。
 */
public enum Color2 implements Light, Liquid {
    RED("红色") {
        @Override
        public String getInfo() {
            return "钢铁侠";
        }
    },
    GREEN("绿色") {
        @Override
        public String getInfo() {
            return "绿巨人";
        }
    },
    Blue("蓝色") {
        @Override
        public String getInfo() {
            return "美国队长";
        }
    };//要用分号结束

    private String desc;//中文描述

    /**
     * 私有构造函数，防止被外部调用，定义后上面的枚举实例类型不会报错
     *
     * @param desc
     */
    private Color2(String desc) {
        this.desc = desc;
    }

    /**
     * 定义方法，返回描述，跟常规类的定义没有区别
     *
     * @return
     */
    public String getDesc() {
        return desc;
    }

    /**
     * enum类允许我们为其定义抽象方法，然后使每个枚举实例都实现该方法，以便产生不同的行为方式，
     * 注意abstract关键字对于枚举类来说并不是必须的
     * 通过这种方式就可以轻而易举地定义每个枚举实例的不同行为方式。
     *
     * @return
     */
    public abstract String getInfo();

    @Override
    public String toString() {//覆盖枚举中的方法，不过只有该方法可以覆盖
        return desc;
    }

    @Override
    public void blink() {
        System.out.println("闪闪发光");
    }

    public static void main(String[] args) {
        for (Color2 color : Color2.values()) {
            System.out.println("name:" + color.name() + ",desc:" + color.getDesc());
//            name:RED,desc:红色
//            name:GREEN,desc:绿色
//            name:Blue,desc:蓝色
        }
        Color2 color2 = Color2.GREEN;
        System.out.println(color2.toString());
        //测试自定义的抽象方法
        //通过这种方式就可以轻而易举地定义每个枚举实例的不同行为方式。
        System.out.println("R:" + Color2.RED.getInfo());//R:钢铁侠
        System.out.println("B:" + Color2.Blue.getInfo());//B:美国队长

        //测试分类
        Liquid liquid = Drinks.COCA_COLA;
        liquid = Liquor.MAOTAI;
    }

}
