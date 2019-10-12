public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Represents the task managed by the chatbot.
     * @param description refers to the details of the task given by the user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the details of the task.
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether a task is marked complete.
     * @return boolean isDone
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the status icon.
     * If the task is complete, a tick is returned otherwise a cross is returned.
     * @return String symbol
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Stringified representation of the task.
     * @return String task
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}