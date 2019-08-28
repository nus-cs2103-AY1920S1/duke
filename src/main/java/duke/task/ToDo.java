package duke.task;

/**
 * ToDO class. Provides a ToDo for new Deadline objects.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo object.
     * @param taskName The name of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Creates a new ToDo object with predefined status.
     * @param status The status of the task.
     * @param taskName The name of the task.
     */
    public ToDo(Status status, String taskName) {
        super(status, taskName);
    }

    /**
     * Returns a string describing the task.
     * @return A string describing the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + "\n";
    }

    /**
     * Returns a string in the format to be saved to disk.
     * @return A string in the format to be saved to disk.
     */
    public String toSaveString() {
        return "T|" + (super.completed == Status.INCOMPLETE ? "0" : "1") + "|" + taskName;
    }

}
