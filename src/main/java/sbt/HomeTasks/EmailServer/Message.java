package main.java.sbt.HomeTasks.EmailServer;

import java.util.Date;

public class Message {
    public String getMessage() {
        return new String(message);
    }

    public Message(String message, Date date) {
        this.message = message;
        this.date = date;
    }

    private String message;

    public Date getDate() {
        return (Date) date.clone();
    }

    public String getUser() {
        return user;
    }

    private String user;
    private Date date;
}
