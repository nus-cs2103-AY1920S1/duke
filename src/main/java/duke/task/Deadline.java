package duke.task;

import duke.DateUtil;
import duke.DukeException;

import java.util.Date;

public class Deadline extends Task {

    private Date by;

    /**
     * Constructs a deadline with the given description and due date.
     *
     * @param description the description of the deadline.
     * @param by the due date of the deadline.
     * @throws DukeException if the deadline has no due date.
     */
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
        return "D | " + this.getIsDoneFlag() + " | " + this.description + " | " + DateUtil.formatDate(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateUtil.formatDate(by) + ")";
    }
}
