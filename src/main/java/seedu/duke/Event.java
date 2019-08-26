package seedu.duke;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    protected DateFormat printFormat = new SimpleDateFormat("dd/MM/yyyy HH.mm aa");
    protected Date dateTime;

    public Event(String description, Date dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + printFormat.format(dateTime) + ")";
    }

    @Override
    public String writeToFile() {
        return ("E " + super.writeToFile() + " | " + dateFormat.format(dateTime));
    }
}
