package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Event type task with a time spot.
 */
public class Event extends Task {

    private Date at;

    /**
     * Constructor of Event.
     * @param description description of event
     * @param at time spot of happening
     * @throws ParseException
     */
    public Event(String description, String at) throws ParseException {
        super(description);
        assert at.length() > 0;
        this.at = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + new SimpleDateFormat("yyyy/MM/dd HH:mm").format(at) + ")";
    }
}
