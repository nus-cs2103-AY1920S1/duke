public class Task {

    private String taskName;
    private boolean isDone;

    public Task(String name) {
        this.isDone = false;
        this.taskName = name;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "\u2713": "\u2718", taskName );
    }

    public void done() {
        this.isDone = true;
    }
}
