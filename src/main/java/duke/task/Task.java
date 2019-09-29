package duke.task;

/**
 * Task class which extends to three different other types of tasks.
 * To Do, Deadline and Event classes.
 */
public class Task {
    /**
     * This field stores the outline of the task.
     */
    private String description;
    /**
     * This field tracks whether the task in the list is done.
     */
    private boolean isDone;

    /**
     * Constructor for the Task class. Keeps track of the description of
     * the task and it will be stored inside a list.
     *
     * @param description Gives the outline of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter method to check the isDone field of the current task.
     *
     * @return either a tick or a cross depending on the isDone status of task.
     */
    private String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

    /**
     * Setter method which changes the isDone field of the task to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Getter method which gives the description of the task.
     *
     * @return the outline of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Formats the string of the current task.
     *
     * @return the formatted string of the current task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
