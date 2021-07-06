package duke.task;

/**
 * Encapsulates a Todo object that inherits from Task class and
 * stores task name and status.
 */

public class Todo extends Task {

    /**
     * The constructor is inherited from Task class.
     * @param taskName String of task name.
     * @param isDone true if the task is done, or false otherwise.
     */
    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    /**
     * Returns a String representation of the task.
     * @returna String showing the status and the task name of a Todo object.
     */
    public String toString() {
        if (isDone) {
            return "[T][✓]" + taskName;
        } else {
            return "[T][✗]" + taskName;
        }
    }

    /**
     * Returns a String of representation of the task that is to b3 recorded in the file.
     * @return String of representation of the task that is to be recorded in the file.
     */
    public String storageFormat() {
        if (isDone) {
            return "T/✓/" + taskName;
        } else {
            return "T/✗/" + taskName;
        }
    }
}
