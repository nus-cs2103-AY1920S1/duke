package seedu.duke;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for creating Event objects.
 */
public class Event extends Task {
    /** Date representing the Event details (date and time). */
    protected Date at;
    /** String representing type of Task. */
    protected String type = "Event";

    /** Constructor.*/
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns String that represents the Event information to be documented in File.
     * @return String of Event information.
     */
    @Override
    public String getFullDescription() {
        return description + " . " + at.getTime();
    }

    /**
     * Returns String representing type of Task object.
     * @return String representing Event type.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Establishes String representation of object.
     * @return String representing the object.
     */
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMMM yyyy hh:mm a");
        String date = formatter.format(at);
        return "[E]" + super.toString() + " (at: " + date + ")";
    }

    @Override
    public void editTask(String description, String details) {
        this.description = description;
        this.at = AddCommand.convertToDate(details);
    }
}
