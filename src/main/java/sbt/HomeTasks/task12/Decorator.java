package sbt.HomeTasks.task12;

import java.util.function.Predicate;

class Decorator<T> {
    private Decorator innerDecorator;
    private Predicate<T> predicate;

    Decorator() {
        this.predicate = t -> true;
    }

    void setResult(Predicate<T> predicate) {
        if (innerDecorator == null) {
            this.predicate = predicate;
            innerDecorator = new Decorator();
        } else  {
            innerDecorator.setResult(predicate);
        }
    }

    boolean getResult(T r) {
        if (this.innerDecorator != null) {
            return predicate.test(r) && innerDecorator.getResult(r);
        }
        return predicate.test(r);
    }
}
