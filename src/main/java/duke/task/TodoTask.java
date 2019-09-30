package duke.task;

/**
 * To-do task is a task that is supposed to be completed in the future.
 */
public class TodoTask  extends Task {
    /**
     * Creates a new TodoTask with a description.
     *
     * @param description The task's description.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns a string of a task that contains
     * its type, description and completion status.
     *
     * @return A string that contains information about a task.
     */
    public String saveInfo() {
        return "todo" + " " + description
                + System.getProperty("line.separator") + completed
                + System.getProperty("line.separator") + priority.toString();
    }

    /**
     * Returns a string containing full information of the TodoTask.
     * This includes type, completion status, description
     * and PriorityLevel.
     *
     * @return A string representation of this TodoTask.
     */
    @Override
    public String toString() {
        if (this.completed) {
            return "[T][✓] " + description + " " + priority.toString();
        } else {
            return "[T][✗] " + description + " " + priority.toString();
        }
    }
}
