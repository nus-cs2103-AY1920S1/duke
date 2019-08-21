package todo;

class Task {
    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    String getStatusIcon() {
        return (isDone ? "[\u2713] " : "[\u2718] "); //return tick or X symbols
    }

    String getDescription() {
        return description;
    }

    void markAsDone() {
        isDone = true;
    }
}
