package duke.task;

/**
 * Represents todo task.
 * The 'Todo' class supports operator (i) toString that
 * returns the string representation of task.
 */
public class Todo extends Task {


    /**
     * Initialises a new instance od Todo task.
     *
     * @param description Description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initialises a new instance od Deadline task.
     * Used for loading the tasks form text file to TaskList.
     *
     * @param isDone      0 or 1 representation of whether a task is done
     * @param description Description of task
     */
    public Todo(String isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns a String representation of task.
     */
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + getDescription() + "\n";
    }
}
