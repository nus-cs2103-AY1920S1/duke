public class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task() {}

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    protected String getStatusIcon() {
        return (isDone ? "+" : "-");
    }

    void done() {
        isDone = true;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toFile() {
        return "";
    }
}