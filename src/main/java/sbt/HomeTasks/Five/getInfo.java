package main.java.sbt.HomeTasks.Five;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by god on 8/31/2016.
 */
public class getInfo {
    public static void main(String[] args) {
        Object object = new String();

        System.out.println("\tMethods: ");
        getMethods(object).forEach(System.out::println);

        System.out.println("\tFields: ");
        getFields(object).forEach(System.out::println);

        System.out.println("\tConstructors: ");
        getConstructors(object).forEach(System.out::println);

        System.out.println("\tGetters: ");
        getGetters(object).forEach(System.out::println);

        System.out.println("\tHierarchy: ");
        getHierarchy(object).forEach(System.out::println);
    }

    public static List<String> getMethods(Object obj) {
        if (obj == null) return null;
        return Arrays.stream(obj.getClass().getDeclaredMethods())
                .map(Method::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getFields(Object obj) {
        if (obj == null) return null;
        return Arrays.stream(obj.getClass().getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getConstructors(Object obj) {
        if (obj == null) return null;
        return Arrays.stream(obj.getClass().getConstructors())
                .map(Constructor::getName)
                .collect(Collectors.toList());
    }

    public static List<String> getGetters(Object obj) {
        if (obj == null) return null;
        return Arrays.stream(obj.getClass().getDeclaredMethods())
                .map(Method::getName)
                .filter(value -> value.startsWith("get"))
                .collect(Collectors.toList());
    }

    public static List<String> getHierarchy(Object obj) {
        if (obj == null) return null;
        List<Class<?>> classes = new ArrayList<>();
        Class<?> current = obj.getClass();
        while (current != null) {
            classes.add(current);
            current = current.getSuperclass();
        }
        for(int i = 0; i < classes.size()/2; ++i) {
            swap(classes, i, classes.size() - i - 1);
        }
        return classes
                .stream()
                .map(value -> value.getName() + "\n      /\\\n      ||" )
                .collect(Collectors.toList());

    }

    private static void swap(List list, int l, int r) {
        Object x = list.get(l);
        list.set(l, list.get(r));
        list.set(r, x);
    }

}
