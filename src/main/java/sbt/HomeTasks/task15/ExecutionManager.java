package sbt.HomeTasks.task15;

public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
