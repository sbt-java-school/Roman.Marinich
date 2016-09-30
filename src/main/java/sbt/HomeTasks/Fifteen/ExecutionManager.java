package main.java.sbt.HomeTasks.Fifteen;

/**
 * Created by god on 9/28/2016.
 */
public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
