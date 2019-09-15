package duke.task;

/**
 * Task class.
 */
public abstract class Task {
    protected boolean isCompleted;
    protected String name;

    /**
     * Constructor for Task object.
     *
     * @param n name of task
     */
    public Task(String n) {
        this.name = n;
        isCompleted = false;
    }
  
    /**
     * Constructor for Task object when loading from history.
     *
     * @param n name of task
     * @param isCompleted indicates if task is completed
     */
    public Task(String n, boolean isCompleted) {
        this.name = n;
        this.isCompleted = isCompleted;
    }

    /**
     * Checks if a task is completed.
     *
     * @return true if task is completed, false otherwise
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Returns name of task.
     *
     * @return name of task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Completes the task.
     */
    public boolean complete() {
        if (this.isCompleted) {
            return false;
        }
        this.isCompleted = true;
        return true;
    }

    /**
     * Returns a string representation of a Task.
     *
     * @return String representation of a Task
     */
    public String toString() {
        String result = "";
        result = this.isCompleted ? result + "O" + "]" : result + "X" + "]";
        result += " " + this.name;
        return result;
    }
}
