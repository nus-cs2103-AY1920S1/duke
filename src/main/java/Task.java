public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        description = desc;
        isDone = false;
    }

    public Task(String desc, boolean isDone) {
        description = desc;
        this.isDone = isDone;
    }

    String getStatusIcon() {
        return isDone ? "\u2713" : "\u2717";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
