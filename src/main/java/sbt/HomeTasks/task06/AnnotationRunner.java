package main.java.sbt.HomeTasks.task06;

public class AnnotationRunner {

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
