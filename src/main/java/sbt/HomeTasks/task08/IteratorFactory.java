package sbt.HomeTasks.task08;

import java.util.Arrays;
import java.util.List;

public class IteratorFactory {
    public static <T> Iterator getIterator(List<T> list) {
        return new Iterator(list);
    }

    public static void main(String[] args) {
        Iterator iterator = IteratorFactory.getIterator(Arrays.asList(1, 2, 3, 4, 5));

        for (Iterator.Element element = iterator.begin(); !element.equals(iterator.end()); element.next()) {
            System.out.print(element.getElement() + " ");
        }
    }
}