package main.java.sbt.HomeTasks.task01;

/**
 * Created by god on 8/7/2016.
 */


public class Contract {
    public static void isNotNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Illegal null");
        }
    }

    public static void isNotEmpty(String s) {
        if(s.isEmpty()) {
            throw new IllegalArgumentException("Illegal empty string");
        }
    }

    public static void isNotNegative(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Illegal negative number");
        }
    }

    public static void isStartWith(String str, String searchedStr) {
        if (!str.startsWith(searchedStr)) {
            throw new IllegalArgumentException("String should start with " + searchedStr);
        }
    }
}
