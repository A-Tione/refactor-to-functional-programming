package com.github.hcsp.functional;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class RefactorToSupplier {
    private static final boolean IS_SUPPLY_STRING = true;
    private static int randomInt() {
        return new Random().nextInt();
    }

    public static void main(String[] args) {
        System.out.println(createObjects());
        System.out.println(createStrings());
        System.out.println(createRandomIntegers());
    }

    // 请尝试使用函数式接口Supplier对三个方法进行重构，消除冗余代码
    // 并尽量尝试使用lambda表达式和方法引用来传递参数
    public static List<Object> create(Supplier<Object> supplier) {
        return create(supplier, false);
    }

    private static List<Object> create(Supplier<Object> supplier, boolean isSupplyString) {
        return Stream.iterate(0, x -> x + 1)
                .limit(10)
                .map(x -> isSupplyString ? supplier.get() + x.toString() : supplier.get())
                .collect(toList());
    }

    public static List<Object> createObjects() {
        return create(Object::new);
    }

    public static List<Object> createStrings() {
        return create(String::new, IS_SUPPLY_STRING);
    }

    public static List<Object> createRandomIntegers() {
        return create(RefactorToSupplier::randomInt);
    }
}
