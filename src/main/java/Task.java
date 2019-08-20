public abstract class Task {
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

    @Override
    public String toString() {
        String mark = (isDone ? "✓" : "✗");
        return String.format("[%s] %s", mark, taskDescription);
    }
}
