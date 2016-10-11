package sbt.HomeTasks.task18.soothsayer;

import sbt.HomeTasks.task18.EmailServer.ChatServer;
import sbt.HomeTasks.task18.EmailServer.Const;
import sbt.HomeTasks.task18.EmailServer.Message;
import sbt.HomeTasks.task18.EmailServer.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class SoothSayer {

    public static void main(String[] args) {
        SoothSayer soothSayer = new SoothSayer();
    }

    private List<Connection> connections;
    private ServerSocket server;

    public SoothSayer() {
        try {
            server = new ServerSocket(Const.PORT);
            connections = new ArrayList<>();
            while (true) {
                Socket socket = server.accept();

                if (socket == null) {
                    continue;
                }

                Connection con = new Connection(socket);

                if (con == null) {
                    continue;
                }

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

        @Override
        public void run() {
            try {
                int guessedNumber = new Random().nextInt(10);
                int inputNumber;

                out.println("SoothSayer guessed a number to 10.");
                out.println("Write your answer:");

                while (true) {
                    System.out.println("");

                    String number = in.readLine();
                    if (number == null) {
                        continue;
                    }
                    inputNumber = Integer.parseInt(number);

                    if (guessedNumber != inputNumber) {
                        out.println("you are wrong. Please try again!");
                    } else {
                        out.println("you are right. My congratulations!");
                        break;
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
                    SoothSayer.this.closeAll();
                    System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Потоки не были закрыты!");
            }
        }
    }
}
