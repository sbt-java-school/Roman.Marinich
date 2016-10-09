package task07;

import org.junit.BeforeClass;
import org.junit.Test;
import sbt.HomeTasks.task07.CachedCalculator;
import sbt.HomeTasks.task07.Calculator;
import sbt.HomeTasks.task07.CalculatorImpl;
import sbt.HomeTasks.task07.Operation;

import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;

public class IsCorrectTest {
    private static Calculator calc = new CalculatorImpl();
    private static Calculator calcProxy;

    @BeforeClass
    public static void init() {
        calc = new CalculatorImpl();
        calcProxy = (Calculator) Proxy.newProxyInstance(CalculatorImpl.class.getClassLoader(),
                CalculatorImpl.class.getInterfaces(),
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
