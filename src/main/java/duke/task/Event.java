package duke.task;

import java.util.Date;

/**
 * Create an Event Task. Description and timing required.
 */
public class Event extends Task {
    private String at;
    private Date date;

    /**
     * Create an Event Task. Description and timing required.
     *
     * @param description Description of the task, in String.
     * @param at Timing of the task, in String.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        date = new Date(at);
    }

    @Override
    public String toWriteFile() {
        return "E | " + getDoneStatus() + " | " + description + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.toString() + ")";
    }
}
