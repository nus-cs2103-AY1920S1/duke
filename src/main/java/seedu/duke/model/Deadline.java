package seedu.duke.model;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Deadline extends Task{

    protected Date by;

    public Deadline(String description, String by)
            throws ParseException {
        super(description);
        this.type = "D";
        this.by = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(by);
    }

    public Deadline(String description, String by, int status)
            throws ParseException{
        super(description, status);
        this.type = "D";
        this.by = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(by);
    }

    @Override
    public String toTextFileString() {
        return super.toTextFileString() + "," +
                new SimpleDateFormat("dd/MM/yyyy HH:mm").format(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
