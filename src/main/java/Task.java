public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    protected int getStatusCode() {
        return isDone ? 1 : 0;
    }

    public void markAsDone() throws DukeException {
        if (isDone) {
            throw new DukeException("This task was marked as done before.");
        }
        this.isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public abstract String serialize();
}