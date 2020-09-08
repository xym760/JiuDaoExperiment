package com.jiudao.experiment.enumTest;

/**
 * @author YuanmaoXu
 * @date 2020/9/2 22:35
 */
public class WeightTable {
    public static void main(String[] args) {
        double earthWeight = Double.parseDouble(args[0]);//物体在地球上的重力
        double mass = earthWeight / Planet.EARTH.surfaceGravity();//获取物体质量
        for (Planet p : Planet.values()) {
            System.out.printf("Weight on %s is %f%n", p.toString(), p.surfaceWeight(mass));
        }
    }
}
