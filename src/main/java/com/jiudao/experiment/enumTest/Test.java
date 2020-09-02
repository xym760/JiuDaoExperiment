package com.jiudao.experiment.enumTest;


import com.fasterxml.jackson.annotation.JsonSubTypes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
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
        System.out.println("colors[0].compareTo(colors[1]):" + colors[0].compareTo(colors[1]));
        System.out.println("colors[0].compareTo(colors[1]):" + colors[0].compareTo(colors[2]));
//        colors[0].compareTo(colors[1]):-1
//        colors[0].compareTo(colors[1]):-2

        //获取该枚举对象的Class对象引用，当然也可以通过getClass方法
        Class<?> clazz = colors[0].getDeclaringClass();
        System.out.println("clazz:" + clazz);//clazz:class com.jiudao.experiment.enumTest.Color
        System.out.println("----------------------------------");

        //name
        System.out.println("colors[0].name():" + colors[0].name());//colors[0].name():RED
        System.out.println("colors[1].name():" + colors[1].name());//colors[1].name():GREEN
        System.out.println("colors[2].name():" + colors[2].name());//colors[2].name():BLUE
        System.out.println("---------------------------------");
        System.out.println("colors[0].toString():" + colors[0].toString());//colors[0].toString():RED
        System.out.println("colors[1].toString():" + colors[1].toString());//colors[1].toString():GREEN
        System.out.println("colors[2].toString():" + colors[2].toString());//colors[2].toString():BLUE
        System.out.println("----------------------------------");

        Color color1 = Enum.valueOf(Color.class, colors[0].name());
        Color color2 = Color.valueOf(Color.class, colors[0].name());
        System.out.println("color1:" + color1);//color1:RED
        System.out.println("color2:" + color2);//color2:RED

        ////下面两个方法是由编译器插入到枚举类中的static方法
        //values()方法的作用就是获取枚举类中的所有变量，并作为数组返回
        Color[] color3 = Color.values();
        System.out.println("color3:" + Arrays.toString(color3));//color3:[RED, GREEN, BLUE]
        //valueOf(String name)方法与Enum类中的valueOf方法的作用类似根据名称获取枚举变量
        Color color = Color.valueOf("RED");
        System.out.println("color:" + color);//color:RED

        //正常使用
        Color[] cs = Color.values();
        //向上转型Enum
        Enum e = Color.RED;
        //无法调用，没有此方法
        //e.values();
        //获取class对象引用
        Class<?> clasz = e.getDeclaringClass();
        if (clasz.isEnum()) {
            //通过Enum的class对象的getEnumConstants方法，我们仍能一次性获取所有的枚举实例常量。
            Color[] csz = (Color[]) clasz.getEnumConstants();
            System.out.println("csz:" + Arrays.toString(csz));//csz:[RED, GREEN, BLUE]
        }

        //枚举与switch
        printName(Color.RED);//红色
        printName(Color.GREEN);//绿色
        printName(Color.BLUE);//蓝色

        //测试反射是否能创建枚举
        Constructor<SingletonEnum> constructor = SingletonEnum.class.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        //创建枚举
//        SingletonEnum singleton = constructor.newInstance("张三", 1);

        //EnumMap基本用法
        //现在我们有一堆size大小相同而颜色不同的数据，需要统计出每种颜色的数量是多少以便将数据录入仓库
        List<Clothes> list = new ArrayList();
        list.add(new Clothes("c001", Color.RED));
        list.add(new Clothes("C003", Color.RED));
        list.add(new Clothes("C004", Color.GREEN));
        list.add(new Clothes("C005", Color.BLUE));
        list.add(new Clothes("C006", Color.BLUE));
        list.add(new Clothes("C007", Color.RED));
        list.add(new Clothes("C010", Color.GREEN));
        //方案1:使用HashMap
        Map<String, Integer> map = new HashMap<>();
        for (Clothes clothes : list) {
            String colorName = clothes.getColor().name();
            Integer count = map.get(colorName);
            if (count != null) {
                map.put(colorName, count + 1);
            } else {
                map.put(colorName, 1);
            }
        }
        System.out.println(map.toString());//{RED=3, BLUE=2, GREEN=2}
        System.out.println("-----------------------");
        //方案2：使用EnumMap
        Map<Color, Integer> enumMap = new EnumMap<>(Color.class);
        for(Clothes clothes:list){
            Color color4 = clothes.getColor();
            Integer count=enumMap.get(color4);
            if(count!=null){
                enumMap.put(color4,count+1);
            }else{
                enumMap.put(color4,1);
            }
        }
        System.out.println(enumMap.toString());//{RED=3, GREEN=2, BLUE=2}
        //EnumMap的构造函数
        //第一种构造方式
        Map<Color,Integer> enumMap1=new EnumMap<Color, Integer>(Color.class);
        //第二种构造方式
        Map<Color,Integer> enumMap2 = new EnumMap<>(enumMap1);
        //第三种构造方式
        Map<Color,Integer> hashMap = new HashMap<>();
        hashMap.put(Color.RED,1);
        hashMap.put(Color.GREEN,2);
        Map<Color,Integer> enumMap3 = new EnumMap<>(hashMap);
    }

    //枚举与switch
    public static void printName(Color color) {
        switch (color) {
            case RED://无需使用Color进行引用
                System.out.println("红色");
                break;
            case GREEN:
                System.out.println("绿色");
                break;
            case BLUE:
                System.out.println("蓝色");
                break;
        }
    }
}

class Clothes {
    private String id;
    private Color color;

    public Clothes(String id, Color color) {
        this.id = id;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}