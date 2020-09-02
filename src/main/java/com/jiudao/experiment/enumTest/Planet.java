package com.jiudao.experiment.enumTest;

/**
 * @author YuanmaoXu
 * @date 2020/9/2 22:19
 */
public enum Planet {
    MERCURY(3.302e+23, 2.439e6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6);
    private final double mass;//千克
    private final double radius;//米
    private final double surfaceGravity;//表面重力：m/s^2

    //万有引力常量m^3/kg s^2
    private static final double G = 6.67300E-11;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass / (radius * radius);
    }

    public double mass() {
        return mass;
    }

    public double radius() {
        return radius;
    }

    public double surfaceGravity() {
        return surfaceGravity;
    }

    public double surfaceWeight(double mass) {//获取物体的重力
        return mass * surfaceGravity; //F=ma
    }
}
