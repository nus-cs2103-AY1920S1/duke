package duke.task;

import duke.util.DateUtil;

import java.util.Date;

import static java.util.Objects.requireNonNull;

/**
 * Represents an event as a {@link Task}.
 */
public class Event extends Task {
    protected Date date;

    public Event(String description, Date date) {
        super(description);
        this.date = requireNonNull(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateUtil.format(date) + ")";
    }

    @Override
    public String stringify() {
        return "E | " + super.stringify() + " | " + DateUtil.format(date);
    }
}
