package duke;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Event extends Task {
    private Date date;
    private String description;

    public Event(String description) {
        super(description);
        this.type = "E";
    }
    public void parseTime(String time) throws ParseException {
        this.date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(time);
    }

    @Override
    public String toString() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.description + this.date;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event event = (Event) o;
            return this.toString().equals(event.toString());
        }
        return false;
    }
}
