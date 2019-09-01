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

    public String saveData() {
        if (isDone) {
            return "1 | " + description;
        }
        return "0 | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}