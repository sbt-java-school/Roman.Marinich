package main.java.sbt.HomeTasks.task02;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Truck truck = (Truck) o;

        if (id != truck.id) return false;
        return type != null ? type.equals(truck.type) : truck.type == null;

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
