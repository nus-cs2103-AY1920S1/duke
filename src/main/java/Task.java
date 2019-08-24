public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * constructor for Task.
     * @param description title of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Retrieved description of task.
     * @return String description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieve a tick or cross symbol depending on isDone.
      * @return String which is either a tick or cross
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Change isDone from false to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}