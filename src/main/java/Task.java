public abstract class Task {
    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return this.isDone ? "\u2713" : "\u2718";
    }

    void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    String toSaveFormat() {
        return String.format("%d|%s", this.isDone ? 1 : 0, this.description);
    }
}
