package duke.task;

/**
 * The class is used to represent the task of to_do type.
 */
public class Todo extends Task {

    /**
     * The constructor of class, to assign string value to the variable.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "T " + super.toString();
    }
}
