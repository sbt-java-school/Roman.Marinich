package test.java.Seven;

import main.java.sbt.HomeTasks.Seven.CachedCalculator;
import main.java.sbt.HomeTasks.Seven.Calculator;
import main.java.sbt.HomeTasks.Seven.MyCalculator;
import main.java.sbt.HomeTasks.Seven.Operation;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;

/**
 * Created by god on 9/14/2016.
 */
public class IsCorrectTest {
    private static Calculator calc = new MyCalculator();
    private static Calculator calcProxy;

    @BeforeClass
    public static void init() {
        calc = new MyCalculator();
        calcProxy = (Calculator) Proxy.newProxyInstance(MyCalculator.class.getClassLoader(),
                MyCalculator.class.getInterfaces(),
                new CachedCalculator(calc));
    }
    @Test
    public void plusCheck() {
        assertEquals(new Double(3), calcProxy.perform(new Operation(1, 2, "PLUS")));
        assertEquals(new Double(3), calcProxy.perform(new Operation(1, 2, "PLUS")));
    }
    @Test
    public void minusCheck() {
        assertEquals(new Double(0), calcProxy.perform(new Operation(1, 1, "MINUS")));
        assertEquals(new Double(0), calcProxy.perform(new Operation(1, 1, "MINUS")));
    }
    @Test
    public void multiplyCheck() {
        assertEquals(new Double(24), calcProxy.perform(new Operation(4, 6, "MULTIPLY")));
    }
}
