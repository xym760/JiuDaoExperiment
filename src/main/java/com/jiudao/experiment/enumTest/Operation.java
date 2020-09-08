package com.jiudao.experiment.enumTest;

public enum Operation {
    PLUS, MINUS, TIMES, DIVIDE;

    // 这种方法不能保证添加
    double apply(double x, double y) {
        switch (this) {
            case PLUS:
                return x + y;
            case MINUS:
                return x - y;
            case TIMES:
                return x * y;
            case DIVIDE:
                return x / y;
        }
        throw new AssertionError("Unknown op: " + this);
    }
}
