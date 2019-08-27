package slave.task;

/**
 * Class representing a Event task
 */
public class Event extends Task {


    private String at;

    /**
     * Constructor for Event task
     * @param description description of event
     * @param id id of event task
     * @param at event date
     */
    public Event(String description, int id, String at) {
        super(description, id);
        this.at = at;
        this.type = TaskType.EVENT;
    }

    /**
     * getter for date of event task
     * @return date of event task
     */
    @Override
    public String getDate() {
        return this.at;
    }

    /**
     * Converts event task to an appropriate String representation
     * @return formatted string of event task to be printed
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}