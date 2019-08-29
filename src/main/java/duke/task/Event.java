package duke.task;

import duke.DukeException;
import duke.Parser;
import duke.Task;

public class Event extends Task {
    protected String date;
    public Event(String desc, String date) {
        super(desc);
        try {
            this.date = Parser.parseDateOrDateTimeToString(date);
        } catch(DukeException e) {
            this.date = date;
        }
    }

    @Override
    public String toString() {
        return String.format("[E][%c] %s (at: %s)", getStatusChar(), description, date);
    }
}
