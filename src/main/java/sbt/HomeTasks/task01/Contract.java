package sbt.HomeTasks.task01;

public abstract class Contract {
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

    static void isNotNegative(int n) {
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
