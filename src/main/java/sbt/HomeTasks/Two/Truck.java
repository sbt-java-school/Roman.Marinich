package main.java.sbt.HomeTasks.Two;

/**
 * Created by god on 8/28/2016.
 */
public class Truck {
    public int id;
    public String type;

    public Truck(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Truck setId(int id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Truck setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return type.hashCode() + id;
    }
}
