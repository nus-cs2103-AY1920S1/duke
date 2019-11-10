package duke.task;

/**
 * Todo class indicated with type "T" which contains a description.
 */
public class Todo extends Task {

    /**
     * Initialises a Todo class.
     * @param description Description of the Todo class.
     */
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * String of the type followed by status icon and description.
     * @return String in the given format.
     */
    public String toString() {
        return "[" + this.type + "]" + super.toString();
    }

}
