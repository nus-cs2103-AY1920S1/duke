public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return tick or X symbols depending of whether the task is completed
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * @return String representing the completion status as tick or cross the description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * @return String that representing the completion status as 1 or 0 and the description
     * for saving in txt file
     */
    public String toSaveString() {
        return (isDone ? 1 : 0) + " | " + description;
    }
}

