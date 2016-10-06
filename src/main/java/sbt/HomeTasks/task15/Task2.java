package main.java.sbt.HomeTasks.task15;

import main.java.sbt.HomeTasks.ThreadPool.ConcreteThread;
import main.java.sbt.HomeTasks.ThreadPool.ThreadPool;

import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        Task2 t = new Task2();

        t.execute(
                () -> System.out.println("all have done!"),
                () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("thread 1");
                },
                () -> System.out.println("thread 2")
        );

    }
}

public class Task2 implements ExecutionManager {

    private class ContextImpl implements Context {
        private ArrayList<ConcreteThread> threads;

        public ContextImpl(ArrayList<ConcreteThread> threads) {
            this.threads = threads;
        }

        @Override
        public long getCompletedTaskCount() {
            return threads
                    .stream()
                    .filter(t -> !t.isAlive() && t.isFailure())
                    .count();
        }

        @Override
        public long getFailedTaskCount() {
            return threads
                    .stream()
                    .filter(t -> t.isFailure())
                    .count();
        }

        @Override
        public long getInterruptedTaskCount() {
            return threads
                    .stream()
                    .filter(t -> t.isInterrupted())
                    .count();
        }

        @Override
        public void interrupt() {
            threads.stream()
                    .forEach(t -> t.interrupt());
        }

        @Override
        public boolean isFinished() {
            return !threads
                    .stream()
                    .anyMatch(t -> t.isAlive());
        }
    }

    private ThreadPool threadPool;

    public Task2() {
        threadPool = new ThreadPool(100);
    }

    public Task2(int sizeOfThreadPool) {
        threadPool = new ThreadPool(sizeOfThreadPool);
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {

        ArrayList<ConcreteThread> workedThreads = new ArrayList<>(tasks.length);

        for (Runnable thread : tasks) {
            workedThreads.add(threadPool.startThread(thread));
        }

        Context context = new ContextImpl(workedThreads);

        threadPool.startThread(
                () -> {
                    workedThreads.forEach(t -> {
                        try {
                            t.join();
                        } catch (Exception e) {
                        }
                    });
                    System.out.println("All have done!");
                }
        );
        return context;
    }
}
