/**
 * Represents a ToDo kind of task.
 * Contains no additional task information on top of the base Task object.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDo. Assumes it is undone.
     *
     * @param description Description of ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for Todo. Allows user to indicate whether it is done.
     *
     * @param description Description of ToDo.
     * @param isDone Boolean representing whether the ToDo is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Represents the done status and description of the task as a string, with a flag indicating it is a ToDo.
     *
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Represents the ToDo as a string in a format suitable for data storage.
     *
     * @return Data storage-friendly string representation of the ToDo.
     */
    @Override
    public String toStorageString() {
        return "T`" + super.toStorageString();
    }
}
