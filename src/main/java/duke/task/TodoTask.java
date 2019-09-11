package duke.task;

/**
 * To-do task is a task that is supposed to be completed in the future.
 */
public class TodoTask  extends Task {
    public TodoTask(String taskDetails) {
        super(taskDetails);
    }

    /**
     * Returns a string of a task that can contain
     * its description, time and completion status.
     *
     * @return string that contains information about a task.
     */
    String saveInfo() {
        return "todo" + " " + taskDetails
                + System.getProperty("line.separator") + completed
                + System.getProperty("line.separator") + priority.toString();
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[T][\u2713] " + taskDetails + " " + priority.toString();
        } else {
            return "[T][\u2717] " + taskDetails + " " + priority.toString();
        }
    }
}
