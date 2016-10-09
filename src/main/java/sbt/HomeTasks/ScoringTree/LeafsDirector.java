package sbt.HomeTasks.ScoringTree;

import java.util.Map;
import java.util.function.BinaryOperator;

class LeafsDirector {
    private LeafsBuilder leafsBuilder;

    LeafsDirector(LeafsBuilder leafsBuilder) {
        this.leafsBuilder = leafsBuilder;
    }

    Tree perfrom(Map<String, Object> information) {
        Tree tree = new Tree();

        String city = (String) information.get("city");
        double salary = (double) information.get("salary");
        double periodOfMonths = (double) information.get("periodOfMonths");
        double amountOfCredit = (double) information.get("amountOfCredit");

        tree.setFunction( leafsBuilder.getDecisionBehavior() );

        Tree leftTree = new Tree();
        leftTree.setFunction(leafsBuilder.getDecisionBehavior());
        leftTree.setLeftLeaf(new Value("Novosibirsk"));
        leftTree.setRightLeaf(new Value(city));

        tree.setLeftLeaf(leftTree);

        Tree rightTree = new Tree();
        rightTree.setFunction(leafsBuilder.getLessBehavior());
        rightTree.setLeftLeaf(new Value(amountOfCredit/periodOfMonths));
        rightTree.setRightLeaf(new Value(salary/2));

        tree.setRightLeaf(rightTree);

        return tree;

    }
}
