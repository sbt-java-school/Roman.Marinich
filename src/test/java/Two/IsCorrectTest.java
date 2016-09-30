package test.java.Two;

import main.java.sbt.HomeTasks.Two.*;
import org.junit.*;
import org.omg.CORBA.TRANSACTION_MODE;

import static main.java.sbt.HomeTasks.Two.Main.KAMAZ;
import static main.java.sbt.HomeTasks.Two.Main.MERCEDES;
import static org.junit.Assert.assertEquals;

/**
 * Created by god on 9/14/2016.
 */
public class IsCorrectTest {
    private static Multimap<String, Truck> multimap;
    private static Truck track;

    @Before
    public void init() {
        multimap = new ListMultimap<>();
        track = new Truck(1, MERCEDES);

        multimap.put(MERCEDES, track);
        multimap.put(MERCEDES, new Truck(2, MERCEDES));
        multimap.put(MERCEDES, new Truck(3, MERCEDES));

        multimap.put(KAMAZ, new Truck(1, KAMAZ));
        multimap.put(KAMAZ, new Truck(2, KAMAZ));
    }

    @Test
    public void keySetCheck() {
        assertEquals("[MERCEDES, KAMAZ]", multimap.keySet().toString());
    }
    @Test
    public void sizeCheck() {
        assertEquals(2, multimap.size());
    }
    @Test
    public void isEmptyCheck() {
        assertEquals(false, multimap.isEmpty());
    }
    @Test
    public void containsKeyCheck() {
        assertEquals(true, multimap.containsKey(MERCEDES));
    }
    @Test
    public void containsValueCheck() {
        assertEquals(true, multimap.containsValue(track));
    }
    @Test
    public void getCheck() {
        assertEquals("[Truck{id=1, type='MERCEDES'}, Truck{id=2, type='MERCEDES'}, Truck{id=3, type='MERCEDES'}]", multimap.get(MERCEDES).toString());
    }
    @Test
    public void removeCheck() {
        assertEquals(true, multimap.remove(MERCEDES));
        assertEquals("[KAMAZ]", multimap.keySet().toString());
        assertEquals(1, multimap.size());
    }
}
