package sbt.HomeTasks.task01;

import java.util.stream.IntStream;

import static sbt.HomeTasks.task01.Contract.*;

public class Solver {
    public static Pair<Integer, Integer> solve(int param, IntStream numbers)  {
        isNotNull(numbers);
        isNotNegative(param);
        final int maxSize = param;
        return numbers
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
