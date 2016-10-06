package main.java.sbt.HomeTasks.task01;

/**
 * Created by god on 8/7/2016.
 */
public class Pair<L, R> {
    private L left;
    private R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() { return left;}
    public R getRight() { return right;}

    public Pair<L, R> setLeft(L left) {
        this.left = left;
        return this;
    }
    public Pair<L, R> setRight(R right) {
        this.right = right;
        return this;
    }

}