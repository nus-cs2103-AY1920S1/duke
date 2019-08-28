public class Task {
    protected String description;
    protected boolean isDone;
    protected int status;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.status = 0;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Changes the status of a task from not done to done
     */
    public void markAsDone() {
        this.isDone = true;
        this.status = 1;
    }

    /**
     * Formats the string to how it should be saved in the .txt file
     * @return String to save in the .txt file
     */
    public String storageString() {
        return "Task|" + status + "|" + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
