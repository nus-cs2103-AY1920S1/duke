public class Task {
    private String description;
    private boolean isDone = false;

    protected Task(String description) {
        this.description = description;
    }

    protected void markAsDone() {
        this.isDone = true;
    }

    private String getStatusIcon() {
        return this.isDone ? "\u2713" : "\u2718"; // return tick or X symbols
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatusIcon(), this.description);
    }
}
