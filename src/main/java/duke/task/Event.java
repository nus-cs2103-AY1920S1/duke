package duke.task;

import java.util.Date;

public class Event extends Task {
    private String at;
    private Date date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        date = new Date(at);
    }

    @Override
    public String toWriteFile() {
        return "E | " + getDoneStatus() + " | " + description + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.toString() + ")";
    }
}
