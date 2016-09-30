package main.java.sbt.HomeTasks.Fifteen;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by god on 9/27/2016.
 */


public class Task<T> {

    private final Lock lock;
    private final Callable<? extends T> callable;
    private T result;
    private RuntimeException taskException;

    public Task(Callable<? extends T> callable) {
        lock = new ReentrantLock(false);
        this.callable = callable;
    }
    public Task(Callable<? extends T> callable, boolean isJustice) {
        lock = new ReentrantLock(isJustice);
        this.callable = callable;
    }

    public T get() {
        if (taskException != null) {
            throw taskException;
        }
        if (result == null) {
            try {
                lock.lock();
                if (result != null) {
                    return result;
                }
                if (taskException != null) {
                    throw taskException;
                }
                result = callable.call();
            }catch (Exception e) {
                taskException = new RuntimeException(e);
                throw taskException;
            }finally {
                lock.unlock();
            }
        }
        return result;
    }

}
