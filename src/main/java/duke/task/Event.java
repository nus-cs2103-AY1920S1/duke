package duke.task;

import duke.task.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;

public class Event extends Task {

    protected String at;
    private SimpleDateFormat eventTime;
    private Date date;

    /**
     * Creates an event task.
     *
     * @param description description of the task
     * @param at time of the event
     */
    public Event(String description, String at) throws DateTimeException {
        super(description);
        this.at = at.trim();
        this.event = "event";
        this.type = "E";

        eventTime = new SimpleDateFormat("dd/MM/yyyy HHmm");
    }

    /**
     * Returns event as the task type.
     *
     * @return type of task
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns time of the event.
     *
     * @return time of event
     */
    @Override
    public String getAt() {
        return this.at;
    }

    /**
     * Converts event time from SimpleDateFormat to a string.
     *
     * @return time of event as a string
     * @throws ParseException when user does not input a time
     * @throws DateTimeException when format of date/time is incorrect
     */
    public String convertEventTime() throws ParseException, DateTimeException {
        this.date = eventTime.parse(this.at);
        return this.eventTime.format(date);
    }

    /**
     * Represents the task in a format suitable for the user to read.
     *
     * @return string representation of task
     */
    @Override
    public String toString() {
        try {
            return "[E]" + super.toString() + " (at: " + convertEventTime() + ")";
        } catch (ParseException exception) {
            return "Error";
        }
    }
}
