package duke.task;

/**
 * Represents an Task object of type ToDo. A <code>ToDo</code> object
 * specifies its item description.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "T " + super.toString();
    }
}
