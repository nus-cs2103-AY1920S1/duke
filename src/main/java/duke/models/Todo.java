package duke.models;

public class Todo extends Task {

    /**
     * Constructor for Todo task.
     *
     * @param description Takes in todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Method to get the string for Todo tasks.
     *
     * @return Returns the formatted string to
     *     be added into tasklist and file.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + description;
    }
}