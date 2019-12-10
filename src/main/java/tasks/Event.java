package tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Event extends Task {

    private String at;
    private Date dateTime;

    // List of acceptable date formats (for this case, just choosing two common patterns)
    private List<String> dateFormats = Arrays.asList("dd/MM/yyyy HHmm", "dd-MM-yyyy HHmm");

    /**
     * Instantiates a new task.Event task.Task.
     * Convert the date/time provided to a SimpleDateFormat object.
     * Can only convert for certain date & time formats.
     *
     * @param description The description of the event
     * @param at          The date & time of which the event is held at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        for (String pattern : dateFormats) {
            SimpleDateFormat dateTime = new SimpleDateFormat(pattern);
            try {
                this.dateTime = dateTime.parse(at);
            } catch (ParseException e) {
            }
        }
    }

    @Override
    public String toString() {
        if (dateTime != null) {
            return "[E]" + super.toString() + " (at: " + dateTime + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

    public String toSave() {
        return "E | " + (getStatus() ? "1" : "0") + " | " + getDescription() + " | " + this.at;
    }
}
