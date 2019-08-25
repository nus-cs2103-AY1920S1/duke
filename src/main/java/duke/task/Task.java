package duke.task;

/**
 * A Class that represents a Task in which the user wishes to complete.
 */
public class Task {
    public String todo;
    public boolean isCompleted;

    /**
     * Constructs a Task which sets the default of isCompleted value to false.
     * @param todo The name of the task.
     */
    public Task(String todo) {
        this.todo = todo;
        this.isCompleted = false;
    }

    /**
     * Constructs a Task based on the name and the isComplated value given.
     * @param todo The name of the task.
     * @param isCompleted Whether the task is Completed or not.
     */
    public Task(String todo, boolean isCompleted) {
        this.todo = todo;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns a string representation of the Task.
     * @return A string representation of the Task.
     */
    public String toString() {
        if (isCompleted) {
            return "[Y] " + this.todo;
        } else {
            return "[N] " + this.todo;
        }
    }
}
