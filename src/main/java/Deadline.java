/**
 * Deadline class to represent a task with a date to be done by.
 */

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Deadline extends Task {

    protected Date date;

    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(by);
    }

    public Deadline(String description, boolean isDone, String by) throws ParseException {
        super(description, isDone);
        this.date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(by);
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy, hmma");
        String formattedDate = formatter.format(this.date);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    @Override
    public String print() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        String formattedDate = formatter.format(this.date);
        if (this.isDone) {
            return "D @ 1 @ " + this.description +  " @ " + formattedDate;
        } else {
            return "D @ 0 @ " + this.description + " @ " + formattedDate;
        }
    }

    public void setDate(Date newDate) {
        this.date = newDate;
    }
}