package duke.task;

import duke.util.DateParser;
import duke.DukeException;

public class Event extends Task {
    /**
     * The date of this event.
     * It is a String to allow the user to custom dates such as 'the day after tomorrow'
     */
    protected String date;

    public Event(String desc, String date) {
        super(desc);
        try {
            this.date = DateParser.parseDateOrDateTimeToString(date);
        } catch (DukeException e) {
            this.date = date;
        }
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description, date);
    }
}
