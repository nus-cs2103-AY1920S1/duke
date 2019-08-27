public class Task {
    private String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "+" : "-"); //return tick or X symbols as + and - respectively
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "| " + getStatusIcon() + " | " + description + " ";
    }
}
