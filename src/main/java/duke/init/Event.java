package duke.init;

/**
 * Implements an event task.
 * @author lyskevin
 */
public class Event extends Task {

    private String dateAndTime;

    /**
     * Constructs an event task with the specified description.
     * @param description The specified description.
     */
    public Event(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    /**
     * Returns the string representation of this event task.
     * @return The string representation of this event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), dateAndTime);
    }

}

