package main.java.sbt.HomeTasks.One;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by god on 8/7/2016.
 */

public class Solver {
    public static Pair<Integer, Integer> runEngine(int param, IntStream numbers)  {
        if (numbers == null || param < 1)
            return null;
        final int[] params = {0, param};
        try {
            List<Integer> result = numbers
                    .filter(
                            value ->
                            {
                                params[0] += value;
                                if (params[0] < params[1])
                                    return true;
                                else
                                {
                                    params[0] -= value;
                                    return false;
                                }
                            }
                    )
                    .boxed()
                    .collect(Collectors.toList());
            return new Pair<>(result.size(), result.stream().reduce((s1, s2) -> s1 + s2).get());
        } catch (IllegalStateException e) {
            return null;
        }
    }
}
