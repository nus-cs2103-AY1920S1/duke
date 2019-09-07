package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    private final Date at;

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    public Event(String description, Date at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toEncodedString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
        String isDone = this.isDone ? "1" : "0";
        return "E | " +  isDone + " | " + this.description + " | " + dateFormat.format(this.at);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
        return "[E]" + super.toString() + " (at: " + dateFormat.format(this.at) + ")";
    }

}
