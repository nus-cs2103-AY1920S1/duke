package duke.task;

import java.util.Date;

/**
 * Tasks used for events.
 */
public class Event extends Task {
    /** Event at date. **/
    private Date at;

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    public Event(String description, Date at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getType() {
        return "E";
    }

    public Date getAt() {
        return at;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), at);
    }
}
