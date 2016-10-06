package main.java.sbt.HomeTasks.task15;

/**
 * Created by god on 9/28/2016.
 */
public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
