public class Task {
    protected String description;
    protected boolean isDone;
    private static int numberOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public static int getTotal() {
        return numberOfTasks;
    }

    public void markAsDone() throws DukeException {
        if (isDone) {
            throw new DukeException("This task was marked as done before.");
        }
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}