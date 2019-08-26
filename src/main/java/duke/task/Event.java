package duke.task;

import duke.DateUtil;
import duke.Duke;
import duke.DukeException;

import java.util.Date;

public class Event extends Task {

    private Date at;

    public Event(String description, Date at) throws DukeException {
        super(description);
        if (at.equals("")) {
            throw new DukeException("The date/time of " + this.getTypeNameWithQuantifier() + " cannot be empty.");
        }
        this.at = at;
    }

    protected String getTypeNameWithQuantifier() {
        return "an event";
    }

    public String toExportFormat() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + DateUtil.formatDate(this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateUtil.formatDate(at) + ")";
    }
}
