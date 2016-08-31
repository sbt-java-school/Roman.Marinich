package main.java.sbt.HomeTasks.Six;

import java.lang.reflect.Method;


class AnnotationRunner {

    public void method1() {
        System.out.println("method1");
    }

    @ForMethod
    public void method2() {
        System.out.println("method2");
    }

    public void method3() {
        System.out.println("method3");
    }

    @ForMethod
    public void method5() {
        System.out.println("method4");
    }

}

public class Annotations {
    public static void main(String[] args) {
        printAllAnntations(new AnnotationRunner());
    }

    public static void printAllAnntations(Object obj) {

        AnnotationRunner runner = new AnnotationRunner();
        Method[] methods = runner.getClass().getMethods();

        for (Method method : methods) {
            ForMethod annos = method.getAnnotation(ForMethod.class);
            if (annos != null) {
                try {
                    method.invoke(runner);
                    System.out.println(method);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
