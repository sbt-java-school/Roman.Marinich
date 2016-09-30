package main.java.sbt.HomeTasks.Two;

/**
 * Created by god on 8/28/2016.
 */
public class Main {
    public static final String MERCEDES = "MERCEDES";
    public static final String KAMAZ = "KAMAZ";

    public static void main(String[] args) {
        Multimap<String, Truck> multimap = new ListMultimap<>();
        Truck tr = new Truck(1, MERCEDES);

        multimap.put(MERCEDES, tr);
        multimap.put(MERCEDES, new Truck(2, MERCEDES));
        multimap.put(MERCEDES, new Truck(3, MERCEDES));

        multimap.put(KAMAZ, new Truck(1, KAMAZ));
        multimap.put(KAMAZ, new Truck(2, KAMAZ));

        String output =
                "output: \n" +
                        "keySet: " + multimap.keySet().toString() + "\n" +
                        "size: " + multimap.size() + "\n" +
                        "isEmpty: " + multimap.isEmpty() + "\n" +
                        "containsKey for " + MERCEDES + ": " + multimap.containsKey(MERCEDES) + "\n" +
                        "containsValue for \"Truck(1, \"MERCEDES\")\": " + multimap.containsValue(tr) + "\n" +
                        "get for " + MERCEDES + ": " + multimap.get(MERCEDES) + "\n" +
                        "remove for " + MERCEDES + ": " + multimap.remove(MERCEDES) + "\n" +
                        "keySet: " + multimap.keySet().toString();

        System.out.println(output);
    }
}
