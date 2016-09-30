package main.java.sbt.HomeTasks.Fifteen;

import main.java.sbt.HomeTasks.One.Pair;
import main.java.sbt.HomeTasks.ThreadPool.ThreadPool;

import java.util.ArrayList;

/**
 * Created by god on 9/27/2016.
 */

public interface Context {
    long getCompletedTaskCount();
    long getFailedTaskCount();
    long getInterruptedTaskCount();
    void interrupt();
    boolean isFinished();
}


//    public Context startThreads(Runnable... runnables) {
//        ArrayList<Pair<InnerThread, Thread>> threads = new ArrayList<>(runnables.length);
//
//        for (Runnable r : runnables) {
//            Pair<InnerThread, Thread> pair = this.threads.poll();
//            pair.getLeft().setRunnable(r);
//            threads.add(pair);
//        }
//
//        return new ThreadPool.ContextImpl(threads);
//
//    }
//
//    public int getCompleteTask() {
//        int value = 0;
//        for (Pair<InnerThread, Thread> p :
//                workedThreads) {
//            value += (!p.getRight().isAlive() && !p.getLeft().isFailure()) ? 1 : 0;
//        }
//        return value;
//    }
//
//    public int getFailedTask() {
//        int value = 0;
//
//        for (Pair<MyThread, Thread> p :
//                workedThreads) {
//            value += (p.getLeft().isFailture()) ? 1 : 0;
//        }
//        return value;
//    }
//
//    public int getInterruptedTask() {
//        int value = 0;
//
//        for (Pair<MyThread, Thread> p :
//                workedThreads) {
//            value += (p.getRight().isInterrupted()) ? 1 : 0;
//        }
//        return value;
//    }
//
//
//private class ContextImpl implements Context {
//    private ArrayList<Pair<MyThread, Thread>> threads;
//
//    public ContextImpl(ArrayList<Pair<MyThread, Thread>> threads) {
//        this.threads = threads;
//    }
//
//    @Override
//    public long getCompletedTaskCount() {
//        return threads
//                .stream()
//                .filter(t -> !t.getRight().isAlive() && t.getLeft().isFailture())
//                .count();
//    }
//
//    @Override
//    public long getFailedTaskCount() {
//        return threads
//                .stream()
//                .filter(t -> t.getLeft().isFailture())
//                .count();
//    }
//
//    @Override
//    public long getInterruptedTaskCount() {
//        return threads
//                .stream()
//                .filter(t -> t.getRight().isInterrupted())
//                .count();
//    }
//
//    @Override
//    public void interrupt() {
//        threads.stream()
//                .forEach(t -> t.getRight().interrupt());
//    }
//
//    @Override
//    public boolean isFinished() {
//        return threads
//                .stream()
//                .anyMatch(t -> t.getRight().isAlive());
//    }
//}