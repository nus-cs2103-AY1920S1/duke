package duke.task;

import duke.DateParser;
import duke.DukeException;

public class Event extends Task {
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
