package duke.task;

import duke.util.DateUtil;

import java.util.Date;

/**
 * Represents an event as a {@link Task}.
 */
public class Event extends Task {
    protected Date at;

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateUtil.format(at) + ")";
    }

    @Override
    public String stringify() {
        return "E | " + super.stringify() + " | " + DateUtil.format(at);
    }
}
