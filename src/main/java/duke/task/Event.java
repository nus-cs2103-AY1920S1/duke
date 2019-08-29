package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a event task.
 */

public class Event extends Task {
    protected String at;
    protected Date date;

    public Event(String description, String at) throws ParseException {
        super(description);
        this.at = at;
        this.date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(at);
    }

    /**
     * Deals with changing the task to file format string.
     *
     * @return task as string.
     */

    public String format() {
        return "E" + super.format() + "|" + at;
    }

    /**
     * Deals with changing the task to print format string.
     *
     * @return task as string.
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
