package sbt.HomeTasks.task18.EmailServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ChatServer {
    private List<User> users;
    private Map<String, List<Message>> userListMap;
    private List<Connection> connections;

    private ServerSocket server;

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
    }

    public ChatServer() {
        try {
            server = new ServerSocket(Const.PORT);
            connections = new ArrayList<>();
            userListMap = new HashMap<>();
            users = new ArrayList<>();

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
                out.flush();

                if (!prepareInput()) {
                    return;
                }
                prepareMessage();

                out.println("Who do you want to send a message?");
                out.print("Input name: ");
                out.flush();
                String toSend = in.readLine();

                out.println("Please write your message. Use \"exit\" for stop this.");
                out.flush();

                String finalName = toSend;
                Optional<Connection> conn = connections.stream().filter(t -> t.getUser().getName().equals(finalName)).findFirst();

                if (conn.isPresent()) {
                    out.println(conn.get().getUser() + " online. Please write your message.");
                    out.flush();
                }

                while (true) {
                    String str = in.readLine();

                    if (str == null) {
                        return;
                    }

                    if (str.equals("exit")) {
                        break;
                    }

                    if (str.split(":").length == 2) {
                        while (true) {
                            toSend = str.split(":")[1].replaceAll("\\s+", "");

                            String finalToSend = toSend;
                            if (users.stream().anyMatch(q -> q.getName().equals(finalToSend))) {
                                out.println("Current user " + toSend);
                                break;
                            } else  {
                                out.println("There is no such user. Write username again!");
                            }
                        }

                    }
                    if (conn.isPresent() && conn.get().isAlive()) {
                        conn.get().out.println(user.getName() + ": " + str);
                    } else {
                        List<Message> messages = userListMap.get(toSend);
                        if (messages == null) {
                            messages = new ArrayList<>();
                            messages.add(new Message(str, new Date(), user.getName()));

                            userListMap.put(toSend, messages);
                        }
                        messages.add(new Message(str, new Date(), user.getName()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }

        private void prepareMessage() {
            List<Message> messages = userListMap.get(user.getName());
            if (messages == null) {
                out.println(String.format("Hi %s, there is no messages for you.", user.getName()));
                out.flush();
            } else {
                out.println(String.format("Hi %s, there is messages for you.", user.getName()));
                out.flush();
                recieveMessage(messages);
            }
        }

        private boolean prepareInput() throws IOException {
            while (true) {
                user = prepareUser();
                if (user == null) {
                    return false;
                }

                if (!users.contains(user) && users.stream().anyMatch(t -> t.getName().equals(user.getName()))) {
                    out.println("Such user already exists. Please choose another login!!");
                    out.flush();
                } else {
                    break;
                }
            }

            if (!users.contains(user)) {
                out.println("There are not such user. You are registered now!");
                out.println("Welcome " + user.getName());
                out.flush();
                users.add(user);
            } else {
                out.println(String.format("Hi %s, you are welcome.", user.getName()));
                out.flush();
            }
            return true;
        }

        private User prepareUser() throws IOException {
            String name;
            out.print("Login: ");
            out.flush();
            name = in.readLine();

            out.print("password: ");
            out.flush();
            String password = in.readLine();

            if (name != null && password != null) {
                user = new User(name, password);
                return user;
            }
            return null;
        }

        public void close() {
            try {
                in.close();
                out.close();
                socket.close();

                connections.remove(this);
//                if (connections.size() == 0) {
//                    ChatServer.this.closeAll();
//                    System.exit(0);
//                }
            } catch (Exception e) {
                System.err.println("Потоки не были закрыты!");
            }
        }
    }
}
