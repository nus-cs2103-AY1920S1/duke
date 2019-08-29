package task;

public abstract class Task {
    public String description;
    public boolean isDone;

    Task(String desc) {
        description = desc;
        isDone = false;
    }

    Task(String desc, boolean isDone) {
        description = desc;
        this.isDone = isDone;
    }

    String getStatusIcon() {
        return isDone ? "\u2713" : "\u2717";
    }

    @Override
    public abstract String toString();
}
