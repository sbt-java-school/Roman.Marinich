package sbt.HomeTasks.ThreadPool;

class ConcreteThread extends Thread {
    private Runnable getRunnable() {
        return runnable;
    }

    void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public Exception getException() {
        return exception;
    }

    private void setException(Exception exception) {
        this.exception = exception;
    }

    public boolean isFailure() {
        return isFailure;
    }

    private void setFailure(boolean failure) {
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
