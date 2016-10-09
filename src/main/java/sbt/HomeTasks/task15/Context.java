package sbt.HomeTasks.task15;

interface Context {
    long getCompletedTaskCount();
    long getFailedTaskCount();
    long getInterruptedTaskCount();
    void interrupt();
    boolean isFinished();
}
