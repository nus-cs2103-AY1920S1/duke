package duke.task;

/**
 * To-do task is a task that is supposed to be completed in the future.
 */
public class TodoTask  extends Task{
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
        return "todo" + " " + taskDetails + System.getProperty("line.separator")
                + completed;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[T][✓] " + taskDetails;
        } else {
            return "[T][✗] " + taskDetails;
        }
    }
}
