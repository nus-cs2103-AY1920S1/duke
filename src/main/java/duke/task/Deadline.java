package duke.task;

import duke.util.DateParser;
import duke.DukeException;

public class Deadline extends Task {
    /**
     * The deadline this task must be completed by.
     * It is a String to allow the user to custom dates such as 'the day after tomorrow'
     */
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
