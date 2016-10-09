package sbt.HomeTasks.task12;

import sbt.HomeTasks.task12.Decorator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IEnumerable<T> {

    private List<T> list = new LinkedList<>();
    private Decorator<T> decorator = new Decorator<>();


    public IEnumerable(List<T> list) {
        this.list.addAll(list);
    }

    public List<T> ToList() {
        return new ArrayList<>(list);
    }

    public IEnumerable <T> Where(Predicate<T> predicate) {
        decorator.setResult(predicate);
        return this;
    }

    public <R> IEnumerable<R> Select(Function<T, R> function) {
        List<R> newList = this
                .list
                .stream()
                .filter(value -> decorator.getResult(value))
                .map(function)
                .collect(Collectors.toList());

        return new IEnumerable<>(newList);
    }

    public T Min() {
        return null;
    }

    public T Max() {
        return null;
    }

}
