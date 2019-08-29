package duke.task;

/**
 * A Task with no time constraints.
 */
public class ToDo extends Task {
    /**
     * Instantiates a new To-Do Task with a given description.
     * @param description The task's description as a String.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the task's type, done status and description as a String.
     * @return A String in the format: [T][+] task_description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
