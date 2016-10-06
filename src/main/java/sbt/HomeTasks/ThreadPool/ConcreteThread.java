package main.java.sbt.HomeTasks.ThreadPool;

/**
 * Created by god on 9/30/2016.
 */
public class ConcreteThread extends Thread {
    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public boolean isFailure() {
        return isFailure;
    }

    public void setFailure(boolean failure) {
        isFailure = failure;
    }

    private Runnable runnable;
    private Exception exception;
    private volatile boolean isFailure;


    @Override
    public void run() {
        try {
            synchronized (this) {
                this.wait();
                if (getRunnable() != null) {
                    runnable.run();
                }
            }
        } catch (Exception e) {
            setException(e);
            setFailure(true);
        }
    }
}
