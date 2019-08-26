package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected Date date;
    protected SimpleDateFormat formatter;

    public Deadline(String description, String date) throws ParseException {
        super(description);
        formatter = new SimpleDateFormat("dd/M/yyyy hh:mm");
        this.date = formatter.parse(date);
    }

    public Deadline(String isDone, String description, String date) throws ParseException {
        super(description);
        formatter = new SimpleDateFormat("dd/M/yyyy hh:mm");
        this.date = formatter.parse(date);
        if (isDone.equals("\u2713")) {
            this.isDone = true;
        }
    }

    public String toDataBase() {
        return "[D] | " + getStatusIcon() + " | " + description + " | " + formatter.format(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatter.format(date) + ")";
    }
}