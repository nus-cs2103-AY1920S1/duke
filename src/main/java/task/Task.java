package task;

/**
 * Generic task that other classes can extend from to be added into the TaskList.
 */
public class Task {
    private boolean isDone;
    private String name;

    /**
     * Constructor for a task, defaults isDone to false (Marks the new task as undone).
     * @param name this task's name
     */
    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    /**
     * Constructor for a task when reading from state file.
     * @param name this task's name
     * @param isDone boolean true or false if this task is done or undone respectively
     */
    public Task(String name, boolean isDone) {
        this.isDone = isDone;
        this.name = name;
    }

    /**
     * Checks if isDone boolean of this task is true or false (If task is completed or not respectively).
     * @return boolean of isDone
     */
    public boolean checkIsDone() {
        return this.isDone;
    }

    /**
     * Gets the string of this task's name.
     * @return String this task's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get DONE or NOT DONE String based on if task is completed or incomplete respectively.
     * @return tick or cross String icon
     */
    public String getStatusIcon() {
        return (isDone ? "DONE" : "NOT DONE");
    }

    /**
     * Changes boolean value of isDone is task by marking it true (Task completed).
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns String representation of task.
     * @return String representation of task in format [(status icon)] (task name)
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getName();
    }

}