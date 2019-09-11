/**
 * Event class to represent a task that occurs on a certain date.
 */

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Event extends Task {
    protected Date date;

    public Event(String description, String at) throws ParseException {
        super(description);
        this.date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(at);
    }

    public Event(String description, boolean isDone, String at) throws ParseException {
        super(description, isDone);
        this.date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(at);
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy, hmma");
        String formattedDate = formatter.format(this.date);
        return "[E]" + super.toString() + " (at: " + formattedDate + ")";
    }

    public void setDate(Date newDate) {
        this.date = newDate;
    }


    @Override
    public String print() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        String formattedDate = formatter.format(this.date);
        if (this.isDone) {
            return "E @ 1 @ " + this.description + " @ " + formattedDate;
        } else {
            return "E @ 0 @ " + this.description + " @ " + formattedDate;
        }
    }
}
