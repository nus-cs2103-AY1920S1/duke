package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is the Event class. It is a type of Task.
 */
public class Event extends Task {

    protected String at;
    protected int hour;
    protected int minute;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        String[] words = at.split(" ");
        hour = (Integer.parseInt(words[1])/100)%12;
        minute = (Integer.parseInt(words[1])%100);
    }

    @Override
    public String getDescription() {
        return description + " /at " + at;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}