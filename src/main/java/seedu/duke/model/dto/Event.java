package seedu.duke.model.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected Date at;

    /**
     * Creates a Event object which extends Task.
     * @param description task description.
     * @param at time specified for the event.
     * @throws ParseException when date 'at' is in incorrect date format.
     */
    public Event(String description, String at) throws ParseException {
        super(description);
        this.type = "E";
        this.at = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(at);
    }

    /**
     * Creates a Event object which extends task, specifying whether if the task is cleared.
     * @param description task description.
     * @param at time specified for the event.
     * @param status task status whether it is cleared or not.
     * @throws ParseException when date 'at' is in incorrect date format.
     */
    public Event(String description, String at, int status) throws ParseException {
        super(description, status);
        this.type = "E";
        this.at = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(at);
    }

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

    @Override
    public String toTextFileString() {
        return super.toTextFileString()
                + "," + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public static void main(String[] args) throws ParseException {
        Event event = new Event("project meeting", "18/09/19 12:00");
        System.out.println(event.getAt());
    }
}
