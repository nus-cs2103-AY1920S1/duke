abstract class Task {

    final String description;
    boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    void markAsDone() {
        this.isDone = true;
    }

    public abstract String toSaveString();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
