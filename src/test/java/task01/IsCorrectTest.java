package test.java.task01;

import main.java.sbt.HomeTasks.task01.*;

import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static main.java.sbt.HomeTasks.task01.Solver.solve;
import static org.junit.Assert.assertEquals;

/**
 * Created by god on 9/7/2016.
 */

public class IsCorrectTest {
    @Test
    public void simpleCheck() {
        List<String> lines;
        int size;
        IntStream numbers;
        try {
            lines = Files.readAllLines(Paths.get("C:\\Users\\god\\IdeaProjects\\sbtjava\\src\\main\\java\\sbt\\HomeTasks\\task01\\input.txt"), Charset.forName("UTF-8"));
            size = Integer.parseInt(lines.get(0).split(" ")[1]);
            numbers = Arrays.stream(lines.get(1).split(" ")).mapToInt(value -> Integer.parseInt(value));
        } catch (Exception e) {
            System.err.println("there is some error with reading the file");
            return;
        }
        Pair<Integer, Integer> pair = solve(size, numbers);
        if (pair == null) {
            System.err.println("Something went wrong");
            return;
        }

        assertEquals("", String.format("%d %d", pair.getLeft(), pair.getRight()), "3 9");
    }
}