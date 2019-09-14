package tasks;

import tasks.Task;

/**
 * Represents a ToDo Object input by the user.
 */
public class ToDo extends Task {

    /**
     * Constructor for todo object.
     *
     * @param name Name of object.
     */
    public ToDo(String name) {
        super(name);
    }

    public String toString() {
        return "T|" + super.toString().trim();
    }
}
