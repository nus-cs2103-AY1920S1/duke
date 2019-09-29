package duke.task;

import java.util.Date;

public class Event extends Task {
    private Date at;

    public Event(String description, Date at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
