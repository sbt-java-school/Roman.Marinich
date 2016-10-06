package main.java.sbt.HomeTasks.EmailServer;

/**
 * Created by god on 10/5/2016.
 */

public class User {
    public String getName() {
        return name;
    }

    public User() {
    }

    public User(String name, int hashPassword) {
        this.name = name;
        this.hashPassword = hashPassword;
    }

    public User(String name, String password) {
        this.name = name;
        this.hashPassword = password.hashCode();
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassword() {
        return hashPassword;
    }

    public void setPassword(String password) {
        this.hashPassword = password.hashCode();
    }

    private String name;
    private int hashPassword;
}
