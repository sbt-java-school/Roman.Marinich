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

    public static void main(String[] args) {
        List<String> lines;
        int size;
        IntStream numbers;
        try {
            lines = Files.readAllLines(Paths.get("C:\\Users\\god\\IdeaProjects\\sbtjava\\src\\main\\java\\sbt\\HomeTasks\\One\\input.txt"), Charset.forName("UTF-8"));
            size = Integer.parseInt(lines.get(0).split(" ")[1]);
            numbers = Arrays.stream(lines.get(1).split(" ")).mapToInt(value -> Integer.parseInt(value));
        } catch (Exception e) {
            System.err.println("there is some error with reading the file");
            return;
        }
        Pair<Integer, Integer> pair = runEngine(size, numbers);
        if (pair == null) {
            System.err.println("Something went wrong");
            return;
        }
        System.out.println(String.format("%d %d", pair.getLeft(), pair.getRight()));
    }

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
