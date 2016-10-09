package sbt.HomeTasks.ScoringTree;

import java.util.function.BinaryOperator;
import sbt.HomeTasks.ScoringTree.*;


class LeafsBuilder {

    BinaryOperator getDecisionBehavior() {
        BinaryOperator binaryOperator = BehaviorFactory.getDecisionBehaviorFactory().getDecisionBehavior();
        return binaryOperator;
    }

    BinaryOperator getLessBehavior() {
        BinaryOperator binaryOperator = BehaviorFactory.getLessDoubleBehaviorFactory().getLessBehavior();
        return binaryOperator;
    }

}
