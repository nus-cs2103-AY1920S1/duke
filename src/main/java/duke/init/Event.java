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
     * Constructs an event task with the specified description and isDone status.
     * @param description The specified description.
     * @param isDone The specified isDone status.
     */
    public Event(String description, boolean isDone, String dateAndTime) {
        super(description, isDone);
        this.dateAndTime = dateAndTime;
    }

    /**
     * Returns the String representation of this event task's date and time.
     * @return The String representation od this event task's date and time.
     */
    public String getDateAndTime() {
        return dateAndTime;
    }

    /**
     * Returns the String representation of this event task's type.
     * @return The String representation of this event task's type.
     */
    public String getType() {
        return "event";
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

