public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Basic constructor for the Task
     * class that only takes in one string
     * for description.
     *
     * @param description description/name of Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Depending on the completion state of task,
     * returns a ticked box (completed) or a
     * crossed box (uncompleted).
     *
     * @return string icon depicting a ticked or crossed box.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks a Task object as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Overridden toString() method for the
     * Task class.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] "  + description;
    }
}