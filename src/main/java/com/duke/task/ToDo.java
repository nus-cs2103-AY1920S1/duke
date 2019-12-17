package com.duke.task;

/**
 * Encapsulates a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object.
     *
     * @param description Task description.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
