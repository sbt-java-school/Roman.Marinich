package sbt.HomeTasks.task07;

import sbt.HomeTasks.task01.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public class CalculatorImpl implements Calculator {

    private Map<String, BinaryOperator<Double>> operators = new HashMap<>();
    private Operation currentOperation;

    public CalculatorImpl() {
        operators.put("PLUS", (left, right) -> left + right);
        operators.put("MINUS", (left, right) -> left - right);
        operators.put("DIVIDE", (left, right) -> left / right);
        operators.put("MULTIPLY", (left, right) -> left * right);
    }

    @SafeVarargs
    public final CalculatorImpl setOperations(Pair<String, BinaryOperator<Double>>... args) {
        Arrays
                .stream(args)
                .filter(value -> value.getLeft() != null && value.getRight() != null)
                .forEach(value -> operators.put(value.getLeft(), value.getRight()));
        return this;
    }

    private Double doOperation(BinaryOperator<Double> function) {
        return function.apply(currentOperation.getLeft(), currentOperation.getRight());
    }

    public Double perform(Operation operation) {
        if (operation == null || operation.getLeft() == null || operation.getRight() == null || operation.getOperation() == null) {
            return null;
        }
        currentOperation = operation;
        BinaryOperator<Double> bo = operators.get(operation.getOperation());
        if (bo == null) {
            return null;
        }
        return doOperation(bo);
    }
}