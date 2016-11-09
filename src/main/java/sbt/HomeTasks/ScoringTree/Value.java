package sbt.HomeTasks.ScoringTree;

class Value<T> extends Leaf{
    public T getValue() {
        return value;
    }

    private T value;

    Value(T value){
        this.value = value;
    }

    public void add(T value) {
        this.value = value;
    }
}
