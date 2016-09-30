package test.java.Streams;

import main.java.sbt.HomeTasks.Seven.CachedCalculator;
import main.java.sbt.HomeTasks.Seven.Calculator;
import main.java.sbt.HomeTasks.Seven.MyCalculator;
import main.java.sbt.HomeTasks.Seven.Operation;
import main.java.sbt.HomeTasks.Streams.IEnumerable;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Proxy;
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
