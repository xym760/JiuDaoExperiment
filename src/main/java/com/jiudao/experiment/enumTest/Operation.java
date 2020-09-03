package com.jiudao.experiment.enumTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YuanmaoXu
 * @date 2020/9/3 22:23
 */
public enum Operation {
    PLUS("+") {
        double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        double apply(double x, double y) {
            return x * y;
        }
    },
    divide("/") {
        @Override
        double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;//绑定特定数据
    }

    @Override
    public String toString() {//覆盖toString,用于输出绑定的特定数据
        return symbol;
    }

    //枚举中定义抽象方法，每个枚举实例各自实现不同，这样添加新的常量，就不会忘记实现apply方法。
    abstract double apply(double x, double y);

    private static final Map<String, Operation> stringToEnum = new HashMap<String, Operation>();

    static {
        for (Operation op : values()) {
            stringToEnum.put(op.toString(), op);
        }
    }

    public static Operation fromString(String symbol) {
        return stringToEnum.get(symbol);
    }

    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);//多个jvm参数用空格分隔
        double y = Double.parseDouble(args[1]);
        for (Operation op : Operation.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}
