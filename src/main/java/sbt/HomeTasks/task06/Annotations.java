package sbt.HomeTasks.task06;

import java.lang.reflect.Method;


public class Annotations {
    public static void main(String[] args) {
        printAllAnnotations(new AnnotationRunner());
    }

    private static void printAllAnnotations(Object obj) {

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
