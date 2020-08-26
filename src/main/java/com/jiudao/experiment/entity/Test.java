package com.jiudao.experiment.entity;

import java.util.Arrays;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
//        Person person = new Person("张三");
//        HashMap<Person,String> map = new HashMap<>();
//        map.put(person, "张三");
//        person.setName("李华");
//        map.put(person, "李华");
//        person.setName("张三");
//        System.out.println(map.get(person));

        //创建枚举数组
        Color[] colors = new Color[]{Color.RED, Color.GREEN, Color.BLUE};
        for (int i = 0; i < colors.length; i++) {
            System.out.println("color[" + i + "].ordinal():" + colors[i].ordinal());
//            color[0].ordinal():0
//            color[1].ordinal():1
//            color[2].ordinal():2
        }

        System.out.println("------------------------------");
        //通过compareTo方法比较,实际上其内部是通过ordinal()值比较的
        System.out.println("colors[0].compareTo(colors[1]):"+colors[0].compareTo(colors[1]));
        System.out.println("colors[0].compareTo(colors[1]):"+colors[0].compareTo(colors[2]));
//        colors[0].compareTo(colors[1]):-1
//        colors[0].compareTo(colors[1]):-2

        //获取该枚举对象的Class对象引用，当然也可以通过getClass方法
        Class<?> clazz = colors[0].getDeclaringClass();
        System.out.println("clazz:"+clazz);//clazz:class com.jiudao.experiment.entity.Color
        System.out.println("----------------------------------");

        //name
        System.out.println("colors[0].name():"+colors[0].name());//colors[0].name():RED
        System.out.println("colors[1].name():"+colors[1].name());//colors[1].name():GREEN
        System.out.println("colors[2].name():"+colors[2].name());//colors[2].name():BLUE
        System.out.println("---------------------------------");
        System.out.println("colors[0].toString():"+colors[0].toString());//colors[0].toString():RED
        System.out.println("colors[1].toString():"+colors[1].toString());//colors[1].toString():GREEN
        System.out.println("colors[2].toString():"+colors[2].toString());//colors[2].toString():BLUE
        System.out.println("----------------------------------");

        Color color1=Enum.valueOf(Color.class, colors[0].name());
        Color color2=Color.valueOf(Color.class, colors[0].name());
        System.out.println("color1:"+color1);//color1:RED
        System.out.println("color2:"+color2);//color2:RED

        ////下面两个方法是由编译器插入到枚举类中的static方法
        //values()方法的作用就是获取枚举类中的所有变量，并作为数组返回
        Color[] color3 = Color.values();
        System.out.println("color3:" + Arrays.toString(color3));//color3:[RED, GREEN, BLUE]
        //valueOf(String name)方法与Enum类中的valueOf方法的作用类似根据名称获取枚举变量
        Color color = Color.valueOf("RED");
        System.out.println("color:"+color);//color:RED

        //正常使用
        Color[] cs = Color.values();
        //向上转型Enum
        Enum e=Color.RED;
        //无法调用，没有此方法
        //e.values();
        //获取class对象引用
        Class<?> clasz=e.getDeclaringClass();
        if(clasz.isEnum()) {
            //通过Enum的class对象的getEnumConstants方法，我们仍能一次性获取所有的枚举实例常量。
            Color[] csz = (Color[])clasz.getEnumConstants();
            System.out.println("csz:"+Arrays.toString(csz));//csz:[RED, GREEN, BLUE]
        }

    }
}
