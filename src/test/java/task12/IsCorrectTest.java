package test.java.task12;

import main.java.sbt.HomeTasks.task12.IEnumerable;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by god on 9/14/2016.
 */
public class IsCorrectTest {

    @Test
    public void simpleCheck() {

        List<Integer> ab = Arrays.asList(1, 2, 3, 4, 5, 6);

        IEnumerable<Integer> ie = new IEnumerable<>(ab);
        List a = ie.Where(t -> t % 2 == 0)
                .Where(t -> t != 4)
                .Select(t -> t * 2)
                .Select(t -> "a" + t)
                .ToList();

        assertEquals("[a4, a12]", a.toString());

    }
}
