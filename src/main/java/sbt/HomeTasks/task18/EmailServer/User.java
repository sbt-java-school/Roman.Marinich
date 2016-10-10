package sbt.HomeTasks.task18.EmailServer;

public class User {
    public String getName() {
        return name;
    }

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (hashPassword != user.hashPassword) return false;
        return name != null ? name.equals(user.name) : user.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + hashPassword;
        return result;
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
