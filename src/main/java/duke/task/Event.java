package duke.task;

import duke.DateUtil;
import duke.DukeException;

import java.util.Date;

public class Event extends Task {

    private Date at;

    /**
     * Constructs an event with the given description and date/time.
     *
     * @param description the description of the event.
     * @param at the date/time of the event.
     * @throws DukeException if the event has no date/time.
     */
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
        return "E | " + this.getIsDoneFlag() + " | " + this.description + " | " + DateUtil.formatDate(this.at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateUtil.formatDate(at) + ")";
    }
}
