package duke.task;

import duke.DateUtil;
import duke.Duke;
import duke.DukeException;

import java.util.Date;

public class Deadline extends Task {

    private Date by;

    public Deadline(String description, Date by) throws DukeException {
        super(description);
        if (by.equals("")) {
            throw new DukeException("The date/time of " + this.getTypeNameWithQuantifier() + " cannot be empty.");
        }
        this.by = by;
    }

    protected String getTypeNameWithQuantifier() {
        return "a deadline";
    }

    public String toExportFormat() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + DateUtil.formatDate(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateUtil.formatDate(by) + ")";
    }
}
