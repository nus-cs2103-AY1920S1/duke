package duke.task;

/**
 * To-do task is a task that is supposed to be completed in the future.
 */
public class TodoTask  extends Task {
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns a string of a task that can contain
     * its description, time and completion status.
     *
     * @return string that contains information about a task.
     */
    public String saveInfo() {
        return "todo" + " " + description
                + System.getProperty("line.separator") + completed
                + System.getProperty("line.separator") + priority.toString();
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[T][\u2713] " + description + " " + priority.toString();
        } else {
            return "[T][\u2717] " + description + " " + priority.toString();
        }
    }
}
