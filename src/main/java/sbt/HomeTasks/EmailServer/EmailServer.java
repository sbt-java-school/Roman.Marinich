package main.java.sbt.HomeTasks.EmailServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class EmailServer {

    private List<User> users;
    private Map<String, List<Message>> userListMap;
    private List<Connection> connections;

    private ServerSocket server;

    public EmailServer() {
        try {
            server = new ServerSocket(Const.PORT);
            userListMap = new HashMap<>();

            while (true) {
                Socket socket = server.accept();

                Connection con = new Connection(socket);
                connections.add(con);

                con.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }

    private void closeAll() {
        try {
            server.close();
            synchronized (connections) {
                Iterator<Connection> iter = connections.iterator();
                while (iter.hasNext()) {
                    ((Connection) iter.next()).close();
                }
            }
        } catch (Exception e) {
            System.err.println("Потоки не были закрыты!");
        }
    }


    private class Connection extends Thread {
        private BufferedReader in;
        private PrintWriter out;
        private Socket socket;
        private User user;

        public Connection(Socket socket) {
            this.socket = socket;

            try {
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

            } catch (Exception e) {
                e.printStackTrace();
                close();
            }
        }

        public User getUser() {
            return user;
        }

        private void recieveMessage(List<Message> messages) {
            for (Message message : messages) {
                out.println(String.format("%s %s: %s", message.getDate().toString(), message.getUser(), message.getMessage()));
            }
        }

        @Override
        public void run() {
            try {
                out.println("Please input the login and password:");

                out.println("Login: ");
                String name = in.readLine();

                out.println("password: ");
                String password = in.readLine();

                user = new User(name, password);

                if (users.contains(user)) {
                    List<Message> messages = userListMap.get(user);
                    recieveMessage(messages);
                } else {
                    out.println("There are not such user. You are registered now!");
                    out.println("Welcome " + user.getName());
                    users.add(user);
                }


                out.print("Who do you want to send a message? \nInput name:");
                String toSend = in.readLine();

                out.println("Please write your message. Use \"exit\" for stop this.");

                Optional<Connection> conn = connections.stream().filter(t -> t.getUser().equals(name)).findFirst();

                if (conn.isPresent()) {
                    out.println(conn.get().getUser() + " online. Please write your message.");
                }

                while (true) {
                    String str = in.readLine();
                    if (str.split(":").length == 2) {
                        toSend = str.split(":")[1].replaceAll("\\s+", "");
                        out.println("Current user " + toSend);
                    }
                    if (conn.isPresent() && conn.get().isAlive()) {
                        conn.get().out.println(user.getName() + ": " + str);
                    } else {
                        userListMap.put(toSend, (List<Message>) new Message(str, new Date()));
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }

        public void close() {
            try {
                in.close();
                out.close();
                socket.close();

                connections.remove(this);
                if (connections.size() == 0) {
                    EmailServer.this.closeAll();
                    System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Потоки не были закрыты!");
            }
        }
    }
}
