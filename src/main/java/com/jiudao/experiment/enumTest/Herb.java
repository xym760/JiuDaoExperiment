package com.jiudao.experiment.enumTest;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Herb {
    public enum Type { ANNUAL, PERENNIAL, BIENNIAL}
    private final String name;
    private final Type type;

    Herb(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        Herb[] garden = new Herb[]{new Herb("百合", Type.ANNUAL),
                new Herb("牛草", Type.PERENNIAL),
                new Herb("羊草", Type.PERENNIAL),
                new Herb("两牛草", Type.BIENNIAL),
                new Herb("两羊草", Type.BIENNIAL)};
        Set<Herb>[] herbsByType = //Indexed by Herb.Type.ordinal()
                (Set<Herb>[]) new Set[Herb.Type.values().length];
        for(int i = 0;i< herbsByType.length;i++) {
            herbsByType[i] = new HashSet<Herb>();
        }
        for(Herb h:garden){
            herbsByType[h.type.ordinal()].add(h);
        }
        //打印结果
        for(int i=0;i<herbsByType.length;i++) {
            System.out.printf("%s: %s%n", Herb.Type.values()[i], herbsByType[i]);
        }

        //更好的实现
        Map<Herb.Type, Set<Herb>> herbsByType1 = new EnumMap<Type, Set<Herb>>(Herb.Type.class);
        for (Herb.Type t : Herb.Type.values()) {
            herbsByType1.put(t , new HashSet<Herb>());
        }
        for(Herb h : garden) {
            herbsByType1.get(h.type).add(h);
        }
        System.out.println(herbsByType1);
    }
}
