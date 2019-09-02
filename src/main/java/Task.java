public class Task {
    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    void markAsDone() {
        isDone = true;
    }

    public StringBuilder saveData() {
        if (isDone) {
            return new StringBuilder("1 | ").append(description);
        }
        return new StringBuilder("0 | ").append(description);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}