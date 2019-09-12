package seedu.duke.trackables;

import seedu.duke.exceptions.InvalidArgumentException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date at;

    public Event(String description, Date at)  {
        super(description);
        this.at = at;
    }

    public Event(String... args) {
        super(args);
        this.at = args[3];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
            + new SimpleDateFormat("d/MM/yyyy HHmm").format(at) + ")";
    }

    @Override
    public String getAsString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("E").append(" | ")
            .append(isDone ? "1" : "0")
            .append(" | ").append(this.description)
            .append(" | ").append(this.at);
        return sb.toString();
    }
}
