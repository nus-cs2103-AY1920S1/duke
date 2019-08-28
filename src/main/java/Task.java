/**
 * Encapsulates a task.
 * Each task has a description and a boolean value
 * to indicate its status of being done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor.
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * @return tick symbol if isDone is true, X symbol if isDone is false.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of isDone to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Overridden toString method. Converts a task object into string form to be used
     * in to-do list display.
     * @return string representation of a task on the to-do list
     */
    @Override
    public String toString() {
        String output = "[" + this.getStatusIcon() + "] " + this.getDescription();
        return output;
    }

    /**
     * Converts a task object into string form to be used
     * in storage text file.
     * @return string representation of a task on the storage text file
     */
    public String toTextFileString() {
        String status = "";
        if (this.isDone == true) {
            status = "1|";
        } else {
            status = "0|";
        }
        String output = status + this.getDescription();
        return output;
    }
}
