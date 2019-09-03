package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Deadline extends Task {

    private String by;
    private Date dateTime;

    // List of acceptable date formats (for this case, just choosing two common patterns)
    private List<String> dateFormats = Arrays.asList("dd/MM/yyyy HHmm", "dd-MM-yyyy HHmm");

    /**
     * Instantiates a new task.Deadline task.Task.
     * Convert the date/time provided to a SimpleDateFormat object.
     * Can only convert for certain date & time formats.
     *
     * @param description The description of the deadline
     * @param by          The date & time of which the task has to be completed by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        for (String pattern : dateFormats) {
            SimpleDateFormat dateTime = new SimpleDateFormat(pattern);
            try {
                this.dateTime = dateTime.parse(by);
            } catch (ParseException e) {
                System.out.println("Unable to convert String to SimpleDateFormat.");
            }
        }
    }

    @Override
    public String toString() {
        if (dateTime != null) {
            return "[D]" + super.toString() + " (by: " + dateTime + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public String toSave() {
        return "D | " + (getStatus() ? "1" : "0") + " | " + getDescription() + " | " + this.by;
    }
}
