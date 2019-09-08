package duke.task;

import duke.util.DukeDate;

public class Event extends Task {
    /**
     * The date of this event.
     * It is a String to allow the user to custom dates such as 'the day after tomorrow'
     */
    protected DukeDate date;

    public Event(String desc, String dateString) {
        super(desc);
        this.date = DukeDate.parse(dateString);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description, date.toString());
    }
}
