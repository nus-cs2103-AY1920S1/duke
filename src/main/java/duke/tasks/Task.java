package duke.tasks;

/**
 * Task class includes Todo, Deadline and Event classes.
 * It is the task that is tracked by the Duke.TaskList.
 */
public class Task {
    protected boolean isDone = false;
    protected String description;
    
    /**
     * Class constructor.
     *
     * @param description details of the respective Task.
     */
    public Task(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    /**
     * Returns the status of the task - 1 for completed and 0 for not completed.
     *
     * @return the status of the task - whether it is completed or not.
     */
    public String getStatusIcon() {
        return (isDone ? "[1]" : "[0]");
    }
    
    /**
     * Method that completes the task.
     */
    public void complete() {
        this.isDone = true;
    }
    
    /**
     * Prints the task with the status and the description together.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}