package duke.task;

import duke.DateParser;
import duke.DukeException;

public class Deadline extends Task {
    protected String date;

    public Deadline(String desc, String date) {
        super(desc);
        try {
            this.date = DateParser.parseDateOrDateTimeToString(date);
        } catch (DukeException e) {
            this.date = date;
        }
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, date);
    }
}
