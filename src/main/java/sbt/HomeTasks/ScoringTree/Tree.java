package sbt.HomeTasks.ScoringTree;

import java.util.function.BinaryOperator;

class Tree extends Leaf {
    void setLeftLeaf(Object leftLeaf) {
        this.leftLeaf = leftLeaf;
    }

    public Object getLeftLeaf() {
        return leftLeaf;
    }

    public Object getRightLeaf() {
        return rightLeaf;
    }

    private Object leftLeaf;
    private Object rightLeaf;

    void setRightLeaf(Object rightLeaf) {
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
