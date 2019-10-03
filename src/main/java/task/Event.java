package task;

import converter.StringDateConverter;

import java.util.Date;

/**
 * Represents an event.
 */
public class Event extends Task {

    protected Date at;

    /**
     * Initializes Event with description and date of event.
     *
     * @param description of the event
     * @param at represents the date of the event
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * Initializes Event with description, date of event and isDone status.
     *
     * @param description of the event
     * @param at represents the date of the event
     * @param isDone true if event is completed. Else false
     */
    public Event(String description, Date at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

    @Override
    public String toString() {
        String strDate = new StringDateConverter().convertDateToString(at);
        return "[E]" + super.toString() + " (at: " + strDate + ")";
    }

    /**
     * Converts task object to format use in file save.
     * @return String that is in file save format
     */
    @Override
    public String toSaveFormat() {
        return String.format("E | %s | %s | %s",
                this.isDone ? "Done" : "Not Done",
                this.getDescription(),
                this.getAt());
    }
}