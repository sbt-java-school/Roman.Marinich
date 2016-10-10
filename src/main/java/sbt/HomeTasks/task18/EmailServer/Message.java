package sbt.HomeTasks.task18.EmailServer;

import java.util.Date;
import java.util.stream.Stream;

public class Message {
    public String getMessage() {
        return new String(message);
    }

    public Message(String message, Date date, String user) {
        this.message = message;
        this.date = date;
        this.user = user;
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
