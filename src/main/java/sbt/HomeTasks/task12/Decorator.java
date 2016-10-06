package main.java.sbt.HomeTasks.task12;

import java.util.function.Predicate;

/**
 * Created by god on 9/21/2016.
 */

public class Decorator<T> {
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
