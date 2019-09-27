package slave.task;

import slave.elements.Tags;

/**
 * Class representing a Event task.
 */
public class Event extends Task {


    private String at;

    /**
     * Constructor for Event task.
     *
     * @param description Description of event.
     * @param id Id of event task.
     * @param at Event date.
     */
    public Event(String description, int id, String at, Tags tags) {
        super(description, id, tags);
        this.at = at;
        this.type = TaskType.EVENT;
    }

    /**
     * Gets date of event task.
     *
     * @return Date of event task.
     */
    @Override
    public String getDate() {
        return this.at;
    }

    /**
     * Converts event task to an appropriate String representation.
     *
     * @return Formatted string of event task to be printed.
     */
    @Override
    public String toString() {
        String formattedString = "[E]" + super.toString() + " (at: " + at + ") " + "(Tags: " + this.tags + ")";
        return formattedString;
    }
}