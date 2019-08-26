package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date date;
    protected SimpleDateFormat formatter;


    public Event(String description, String date) throws ParseException {
        super(description);
        formatter = new SimpleDateFormat("dd/M/yyyy hh:mm");
        this.date = formatter.parse(date);
    }

    public Event(String isDone, String description, String date) throws ParseException {
        super(description);
        formatter = new SimpleDateFormat("dd/M/yyyy hh:mm");
        this.date = formatter.parse(date);
        if (isDone.equals("\u2713")) {
            this.isDone = true;
        }
    }

    public String toDataBase() {
        return "[E] | " + getStatusIcon() + " | " + description + " | " + formatter.format(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatter.format(date) + ")";
    }
}