package duke.task;

/**
 * Encapsulates a todo task object.
 */
public class ToDo extends Task {
    protected String by;

    public ToDo(String description) {
        super(description);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String textFormat() {
        return String.format("T | %d | %s", getStatusCode(), description);
    }
}