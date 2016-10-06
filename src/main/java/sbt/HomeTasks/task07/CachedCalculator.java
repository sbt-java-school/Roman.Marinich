package main.java.sbt.HomeTasks.task07;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SBTJavastudent on 01.09.2016.
 */
public class CachedCalculator implements java.lang.reflect.InvocationHandler {
    private Object obj;
    private Map<Operation, Double> cache = new HashMap<>();

    public CachedCalculator(Object f1) {
        obj = f1;
    }

    public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
            throws Throwable {
        if (args == null || args.length < 1) {
            return null;
        }
        Double result = result = cache.get(args[0]);
        if (result != null) {
            System.out.print("[Found cash: " + result + "] ");
        } else {
            result = (Double) method.invoke(obj, args);
            cache.put((Operation) args[0], result);
        }
        return result;
    }
}
