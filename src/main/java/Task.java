public class Task {
    String taskDescription;
    boolean isDone;

    public Task(String s) {
        taskDescription = s;
        isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String mark = (isDone ? "✓" : "✗");
        return String.format("[%s] %s", mark, taskDescription);
    }
}
