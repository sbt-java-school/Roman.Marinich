package sbt.HomeTasks.task01;

import java.util.stream.IntStream;

import static sbt.HomeTasks.task01.Contract.*;

public class Solver {
    public static Pair<Integer, Integer> solve(final int maxSize, IntStream weights)  {
        isNotNull(weights);
        isNotNegative(maxSize);
        return weights
                .collect(
                        () -> new Pair<>(0, 0),
                        (l, r) -> {
                            if ( l.getRight() + r < maxSize ) {
                                l.setLeft(l.getLeft() + 1);
                                l.setRight(l.getRight() + r);
                            }
                        },
                        (l, r) -> r = l
                );
    }
}
