/**
 * Represents a task of type ToDo.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo task with the given description.
     * @param description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString() + super.formatTags());
    }
}