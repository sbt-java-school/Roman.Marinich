package sbt.HomeTasks.task01;

public class Pair<L, R> {
    private L left;
    private R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() { return left;}
    public R getRight() { return right;}

    Pair<L, R> setLeft(L left) {
        this.left = left;
        return this;
    }
    Pair<L, R> setRight(R right) {
        this.right = right;
        return this;
    }

}