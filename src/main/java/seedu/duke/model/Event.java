package seedu.duke.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task{
    protected Date at;

    public Event(String description, String at) throws ParseException {
        super(description);
        this.type = "E";
        this.at = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(at);
    }

    public Event(String description, String at, int status) throws ParseException{
        super(description, status);
        this.type = "E";
        this.at = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(at);
    }

    @Override
    public String toTextFileString() {
        return super.toTextFileString() + "," + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
