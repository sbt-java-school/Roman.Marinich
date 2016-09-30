package main.java.sbt.HomeTasks.ThreadPool;

import main.java.sbt.HomeTasks.One.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import main.java.sbt.HomeTasks.Fifteen.Context;

public class ThreadPool {

    private Queue<InnerThread> threads = new ArrayDeque<>();

    private int size = 0;
    private int minSize;

    public void setSize(int size) {
        int temp = size - this.size;
        if (temp <= 0) return;

        for (int i = 0; i < temp; ++i) {
            InnerThread innerThread = new InnerThread();
            innerThread.start();
            threads.add(innerThread);
        }
        this.size = threads.size();
    }

    public ThreadPool(int size, int minSize) {
        if (size < 1 || minSize < 1) {
            throw new RuntimeException();
        }
        this.size = size;
        this.minSize = minSize;
        setSize(size);
    }
    public ThreadPool(int minSize) {
        if (minSize < 1) {
            throw  new RuntimeException();
        }
        this.minSize = minSize;
        setSize(100);
    }

    public InnerThread startThread(Runnable runnable) {
        if (runnable == null) {
            return null;
        }
        if (size < minSize) {
            setSize(minSize - size);
        }

        InnerThread thread = threads.poll();
        thread.setRunnable(runnable);

        synchronized (thread) {
            thread.notify();
        }
        return thread;
    }

}

class Main {
    public static void main(String[] args) throws Exception {
        ThreadPool tp = new ThreadPool(200);
        for (int i = -20; i < 20; i++) {
            int final_i = i;
            //tp.startThread(() -> System.out.println(final_i));

            tp.startThread(() -> {
                int x = 10;
                double y = 10.0 / final_i;
                System.out.println(y);
                if (final_i == 10) {
                    throw new RuntimeException();
                }
            });

        }
    }
}
