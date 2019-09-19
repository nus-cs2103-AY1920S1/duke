package softeng.tasks;

import java.util.Objects;
import softeng.date.Date;

/**
 * Represents an Event task. An <code>Event</code> object corresponds to the
 * description of the task and the date. e.g. <code>party at Saturday</code>
 */
public class Event extends Task{
    protected String at;
    protected Date atDate;
    
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public Event(String description, Date atDate) {
        super(description);
        this.atDate = atDate;
    }

    @Override
    public String toString() {
        String str = Objects.isNull(atDate) ? at : atDate.toString();
        return "[E]["+ this.getStatusIcon() +"] " + super.toString() + " (at: " + str + ")";
    }

    /**
     * Returns a string representing the task list to be saved in local file.
     * @return A string representation of task list
     */
    @Override
    public String toSave() {
        String done = isDone ? "1 | " : "0 | ";
        return "T | " + done + description + " | " + at;
    }
}
