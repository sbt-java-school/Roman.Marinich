package main.java.sbt.HomeTasks.Streams;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by god on 9/21/2016.
 */

class Decorator<T> {
    private Decorator innerDecorator;
    private Predicate<T> predicate;

    public Decorator() {
        this.predicate = t -> true;
    }

    public void setResult(Predicate<T> predicate) {
        if (innerDecorator == null) {
            this.predicate = predicate;
            innerDecorator = new Decorator();
        } else  {
            innerDecorator.setResult(predicate);
        }
    }

    public boolean getResult(T r) {
        if (this.innerDecorator != null) {
            return predicate.test(r) && innerDecorator.getResult(r);
        }
        return predicate.test(r);
    }
}

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
        decorator.setResult(o -> predicate.test((T)o));
        return this;
    }

    public <R> IEnumerable<R> Select(Function<T, R> function) {
        List<R> newList = this
                .list
                .stream()
                .filter(value -> decorator.getResult(value))
                .map(function::apply)
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
