import java.io.*;

public abstract class Task implements Serializable {
    protected String taskDescription;
    protected boolean isDone;
    protected static int NUMBEROFTASKS = 0;

    public Task(String s) {
        taskDescription = s;
        isDone = false;
        NUMBEROFTASKS++;
    }

    public void markAsDone() {
        isDone = true;
    }

    public static int getNumberOfTasks() {
        return NUMBEROFTASKS;
    }

    public static void decrementNumber() {
        NUMBEROFTASKS--;
    }

    public static void updateSize(int size) {
        NUMBEROFTASKS = size;
    }

    @Override
    public String toString() {
        String mark = (isDone ? "✓" : "✗");
        return String.format("[%s] %s", mark, taskDescription);
    }
}
