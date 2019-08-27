public class Task {
    private String description;
    private boolean isDone = false;

    protected Task(String description) {
        this.description = description;
    }
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    private String getStatusIcon() {
        return this.isDone ? "\u2713" : "\u2718"; // return tick or X symbols
    }

    public String toSaveFormat() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
