public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        description = desc;
        isDone = false;
    }

    String getStatusIcon() {
        return isDone ? "x" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
