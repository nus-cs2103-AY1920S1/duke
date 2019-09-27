package slave.task;

import slave.elements.Tags;

import java.util.Optional;

/**
 * Class representing a Deadline task.
 */
public class Deadline extends Task {


    private String by;

    /**
     * Constructor for Deadline task.
     *
     * @param description Description of deadline.
     * @param id Id of deadline task.
     * @param by Deadline date.
     */
    public Deadline(String description, int id, String by, Tags tags) {
        super(description, id, tags);
        this.by = by;
        this.type = TaskType.DEADLINE;
    }

    /**
     * Gets date of deadline task.
     *
     * @return date of deadline task.
     */
    @Override
    public String getDate() {
        return this.by;
    }

    /**
     * Converts deadline task to an appropriate String representation.
     *
     * @return Formatted string of deadline task to be printed.
     */
    @Override
    public String toString() {
        String formattedString =  "[D]" + super.toString() + " (by: " + by + ") " + "(Tags: " + this.tags + ")";
        return formattedString;
    }
}
