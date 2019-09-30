package duke.tasks;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Event class that can be created by the user. Handles all Duke.tasks with events.
 */
public class Event extends Task {
    
    protected Date at;
    protected SimpleDateFormat format = new SimpleDateFormat();
    
    /**
     * Class constructor.
     *
     * @param description details of the respective task.
     * @param at          event date.
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }
    
    /**
     * Prints the event with the status, the description and the event date together.
     */
    @Override
    public String toString() {
        format = new SimpleDateFormat("EEEE, MMM d, HH:mm");
        return "[E]" + super.toString() + "(at: " + format.format(at) + ")";
    }
}

