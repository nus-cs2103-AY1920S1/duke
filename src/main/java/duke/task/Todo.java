package duke.task;

public class Todo extends Task {

    /**
     * Constructor to create the Todo object.
     *
     * @param description The task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * To get string with type and description for duplication check.
     *
     * @return String to check for duplicates.
     */
    public String getDuplicateCheckString() {
        return String.format("T %s", description);
    }

    /**
     * The proper representation of the task, with [T], icon and description.
     *
     * @return The output representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}