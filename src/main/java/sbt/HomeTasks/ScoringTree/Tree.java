package sbt.HomeTasks.ScoringTree;

import java.util.function.BinaryOperator;

class Tree extends Leaf {
    void setLeftLeaf(Leaf leftLeaf) {
        this.leftLeaf = leftLeaf;
    }

    public Leaf getLeftLeaf() {
        return leftLeaf;
    }

    public Leaf getRightLeaf() {
        return rightLeaf;
    }

    private Leaf leftLeaf;
    private Leaf rightLeaf;

    void setRightLeaf(Leaf rightLeaf) {
        this.rightLeaf = rightLeaf;
    }

    void setFunction(BinaryOperator function) {
        this.function = function;
    }

    private BinaryOperator function;


    Value perform() {
        if (leftLeaf instanceof Value && rightLeaf instanceof Value) {
            return new Value(function.apply(((Value) leftLeaf).getValue(), ((Value) rightLeaf).getValue()));
        }

        if (leftLeaf instanceof Value) {
            rightLeaf = ((Tree) rightLeaf).perform();
            return new Value(function.apply(((Value) leftLeaf).getValue(), ((Value) rightLeaf).getValue()));
        }

        if (rightLeaf instanceof Value) {
            leftLeaf = ((Tree) leftLeaf).perform();
            return new Value(function.apply(((Value) leftLeaf).getValue(), ((Value) rightLeaf).getValue()));
        }

        leftLeaf = ((Tree) leftLeaf).perform();
        rightLeaf = ((Tree) rightLeaf).perform();
        return new Value(function.apply(((Value) leftLeaf).getValue(), ((Value) rightLeaf).getValue()));
    }
}
