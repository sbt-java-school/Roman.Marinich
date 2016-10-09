package sbt.HomeTasks.ScoringTree;


import java.util.function.BinaryOperator;

class LessDoubleBehaviorFactory {
    BinaryOperator getLessBehavior() {
        return (left, right) -> (double) left < (double) right;
    }
}

class EqualBehaviorFactory {
    BinaryOperator getDecisionBehavior() {
        return Object::equals;
    }
}

abstract class BehaviorFactory {

    static LessDoubleBehaviorFactory getLessDoubleBehaviorFactory() {
        return new LessDoubleBehaviorFactory();
    }

    static EqualBehaviorFactory getDecisionBehaviorFactory() {
        return new EqualBehaviorFactory();
    }

}
