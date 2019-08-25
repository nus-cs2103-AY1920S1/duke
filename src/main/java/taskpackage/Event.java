package taskpackage;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Event extends Task {

    protected Date date;
    protected SimpleDateFormat formatter;


    public Event(String description, String date) throws ParseException {
        super(description);
        formatter = new SimpleDateFormat("dd/M/yyyy hh:mm");
        this.date = formatter.parse(date);
    }

    public Event(String isDone, String description, String date) {
        super(description);
        this.date = date;
        if (isDone.equals("\u2713")) {
            this.isDone = true;
        }
    }

    protected String toDataBase() {
        return "[E] | " + getStatusIcon() + " | " + description + " | " + date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatter.format(date) + ")";
    }
}