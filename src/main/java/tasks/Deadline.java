package tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Deadline extends Task {
    private Date by;

    /**
     * Constructor for Deadline object.
     * @param description Description of Deadline.
     * @param by Deadline for task.
     * @throws ParseException Exception when given string cannot be converted into date.
     */
    public Deadline(String description, String by) throws ParseException {
        super(description);
        Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(by);
        this.by = date;
    }

    public Date getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }

}
