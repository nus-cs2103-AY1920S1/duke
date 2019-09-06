package duke.task;

import java.util.Date;

public class Event extends Task {
    private Date at;

    public Event(String description, Date at) {
        super(description);
        assert at != null : "Event date is null";
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
