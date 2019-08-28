package seedu.duke.model;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Deadline extends Task {

    protected Date by;

    /**
     * Creates a Deadline object which extends Task.
     * @param description task description.
     * @param by specified time for the deadline.
     * @throws ParseException when by has incorrect date format.
     */
    public Deadline(String description, String by)
            throws ParseException {
        super(description);
        this.type = "D";
        this.by = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(by);
    }

    /**
     * Creates a Deadline object with desc, by and task status.
     * @param description task description.
     * @param by time specified for the deadline.
     * @param status task status whether it is cleared.
     * @throws ParseException when by has incorrect date format.
     */
    public Deadline(String description, String by, int status)
            throws ParseException {
        super(description, status);
        this.type = "D";
        this.by = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(by);
    }

    @Override
    public String toTextFileString() {
        return super.toTextFileString() + ","
                + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
