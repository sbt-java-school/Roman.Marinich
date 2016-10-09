package sbt.HomeTasks.task07;

import sbt.HomeTasks.task01.Pair;

import java.util.Objects;

public class Operation {

    private final Pair<Double, Double> operands;
    private final String operation;

    public Operation(double left, double right, String operation) {
        this.operands = new Pair<>(left, right);
        this.operation = operation;
    }

    Double getLeft() {
        return operands.getLeft();
    }

    Double getRight() {
        return operands.getRight();
    }

    String getOperation() {
        return operation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation1 = (Operation) o;

        return getLeft() != null ? getLeft().equals(operation1.getLeft()) : operation1.getLeft() == null && (getRight() != null ? getRight().equals(operation1.getRight()) : operation1.getRight() == null && Objects.equals(getOperation(), operation1.getOperation()));
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
