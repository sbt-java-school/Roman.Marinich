package sbt.HomeTasks.ThreadPool;

import java.util.ArrayDeque;
import java.util.Queue;

public class ThreadPool {
    public static void main(String[] args) throws Exception {
        ThreadPool tp = new ThreadPool(200);
        for (int i = -20; i < 20; i++) {
            int final_i = i;
            tp.startThread(() -> {
                double y = 10.0 / final_i;
                System.out.println(y);
                if (final_i % 2 == 0) {
                    throw new RuntimeException();
                }
            });

        }
    }

    private Queue<ConcreteThread> threads = new ArrayDeque<>();

    private volatile int size = 0;
    private volatile int minSize;

    public void setSize(int size) {
        int temp = size - this.size;
        if (temp <= 0) return;

        for (int i = 0; i < temp; ++i) {
            ConcreteThread concreteThread = new ConcreteThread();
            concreteThread.start();
            threads.add(concreteThread);
        }
        this.size = threads.size();
    }

    public ThreadPool(int size, int minSize) {
        if (size < 1 || minSize < 1) {
            throw new IllegalArgumentException("Input parameters should be good");
        }
        this.size = size;
        this.minSize = minSize;
        setSize(size);
    }
    private ThreadPool(int minSize) {
        if (minSize < 1) {
            throw new IllegalArgumentException("Input parameters should be good");
        }
        this.minSize = minSize;
        setSize(100);
    }

    private ConcreteThread startThread(Runnable runnable) {
        if (runnable == null) {
            return null;
        }

        synchronized (this) {
            if (size < minSize) {
                setSize(minSize - size);
            }
        }

        ConcreteThread thread;
        synchronized (this) {
            thread = threads.poll();
            thread.setRunnable(runnable);
        }

        synchronized (thread) {
            thread.notify();
        }
        return thread;
    }
}