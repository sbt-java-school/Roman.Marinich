package sbt.HomeTasks.ScoringTree;

import java.util.HashMap;
import java.util.Map;

public abstract class ScoringTree {

    public static void main(String[] args) {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("city", "Omsk");
        hm.put("salary", 25.);
        hm.put("periodOfMonths", 3.);
        hm.put("amountOfCredit", 4.);

        perform(hm);
    }

    private static void perform(Map<String, Object> information) {
        LeafsDirector leafsDirector = new LeafsDirector(new LeafsBuilder());
        Tree scoringTree = leafsDirector.perfrom(information);

        Value value = scoringTree.perform();
        System.out.println("the credit is accepted: " + value.getValue());
    }
}
