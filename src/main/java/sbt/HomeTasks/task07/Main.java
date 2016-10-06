package main.java.sbt.HomeTasks.task07;

import java.lang.reflect.Proxy;

/**
 * Created by SBTJavastudent on 01.09.2016.
 */
class Main {
    public static void main(String[] args) {
        Calculator calc = new CalculatorImpl();

        Calculator calcProxy = (Calculator) Proxy.newProxyInstance(CalculatorImpl.class.getClassLoader(),
                CalculatorImpl.class.getInterfaces(),
                new CachedCalculator(calc));
        System.out.println(String.format("1 + 2 = " + calcProxy.perform(new Operation(1, 2, "PLUS"))));
        System.out.println(String.format("1 + 2 = " + calcProxy.perform(new Operation(1, 2, "PLUS"))));
        System.out.println(String.format("1 - 1 = " + calcProxy.perform(new Operation(1, 1, "MINUS"))));
        System.out.println(String.format("4 * 6 = " + calcProxy.perform(new Operation(4, 6, "MULTIPLY"))));
        System.out.println(String.format("1 - 1 = " + calcProxy.perform(new Operation(1, 1, "MINUS"))));
    }
}
