package main.java.sbt.HomeTasks.Seven;

import main.java.sbt.HomeTasks.One.Pair;

public class Operation {

    private final Pair<Double, Double> operands;
    private final String operation;

    Operation(double left, double right, String operation) {
        this.operands = new Pair<>(left, right);
        this.operation = operation;
    }

    public Double getLeft() {
        return operands.getLeft();
    }

    public Double getRight() {
        return operands.getRight();
    }

    public String getOperation() {
        return operation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation1 = (Operation) o;

        if (getLeft() != null ? !getLeft().equals(operation1.getLeft()) : operation1.getLeft() != null) return false;
        if (getRight() != null ? !getRight().equals(operation1.getRight()) : operation1.getRight() != null)
            return false;
        return getOperation() == operation1.getOperation();
    }

    @Override
    public int hashCode() {
        int result = getLeft() != null ? getLeft().hashCode() : 0;
        result = 31 * result + (getRight() != null ? getRight().hashCode() : 0);
        result = 31 * result + (getOperation() != null ? getOperation().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "left=" + operands.getLeft() +
                ", right=" + operands.getRight() +
                ", operation=" + operation +
                '}';
    }
}
