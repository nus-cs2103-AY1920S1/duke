package duke.task;

import duke.DukeException;
import duke.Parser;
import duke.Task;

public class Deadline extends Task {
    protected String date;
    public Deadline(String desc, String date) {
        super(desc);
        try {
            this.date = Parser.parseDateOrDateTimeToString(date);
        } catch(DukeException e) {
            this.date = date;
        }
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, date);
    }
}
