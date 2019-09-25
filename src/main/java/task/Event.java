package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected Date date;
    private SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy, hhmma");


    public Event(String name, String dateString) {
        this.name = name;

        try {
            Date dateTime = parser.parse(dateString);
            this.date = dateTime;
        } catch (ParseException parseExp) {
            System.err.println(parseExp);
        }

        this.isDone = false;
    }

    public String toFile() {
        if (isDone) {
            return "E-1-" + name + "-" + parser.format(date);
        } else {
            return "E-0-" + name + "-" + parser.format(date);
        }
    }

    public String toString() {
        if (isDone) {
            return "[E][✓] " + name + " (at: " + formatter.format(date) + ")";
        } else {
            return "[E][✗] " + name + " (at: " + formatter.format(date) + ")";
        }
    }
}
