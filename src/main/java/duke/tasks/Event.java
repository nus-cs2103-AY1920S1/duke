package duke.tasks;

import duke.exceptions.DukeException;
import duke.utils.DukeDate;

import java.text.ParseException;

/** Implements the logic behind an Event Task */
public class Event extends Task {
    private DukeDate startTime;
    private DukeDate endTime;

    /**
     * Constructor
     *
     * @param description String describing title/details of Event
     * @param startTime   String describing start time of Event (in the format specified in DukeDate.dateFormatString)
     * @param endTime     String describing end time of Event (in the format specified in DukeDate.dateFormatString)
     * @throws DukeException
     */
    public Event(String description, String startTime, String endTime) throws DukeException {
        super(description);
        try {
            this.startTime = new DukeDate(startTime);
            this.endTime = new DukeDate(endTime);
        } catch (ParseException e) {
            throw new DukeException("Unable to created Event object. Time in invalid format: " + e.getMessage());
        }

    }

    /**
     * String representation of Event object
     *
     * @return String representation of Event object
     */
    public String toString() {
        String statusIcon = getStatusIcon();
        return "[E][" + statusIcon + "] " + this.description + " (at: " + this.startTime + " - " + this.endTime + ")";
    }

    /**
     * Generates a String representation of the Event Task in a format
     * that is compatible for the Storage object to read and write.
     *
     * @return String representation of the Task (compatibility with Storage class)
     */
    public String getStorageFormat() {
        String storageString = "E | " + super.getStorageFormat() + " | " + this.startTime + " | " + this.endTime;
        return storageString;
    }
}
