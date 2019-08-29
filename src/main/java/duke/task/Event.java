package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * one of the tasks used to record events
 */
public class Event extends Task {
    private Date at;

    /**
     * A constructor to write in date and content of the task.
     * @param description the content of the task
     * @param at the event time, with a fixed format
     * @throws ParseException
     */
    public Event(String description, String at) throws ParseException {
        super(description);
        this.at = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(at);
    }
    /**
     * the Overriding method
     * @return String that in event format
     */
    @Override
    public String toString() {
        return "E -- " + super.toString() + " -- " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(at);
    }
}
