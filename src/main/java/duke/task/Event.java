package duke.task;

import java.util.Date;

public class Event extends Task {
    Date time;

    /**
     * Constructor.
     */
    public Event(String name, Date time) {
        super(name);
        this.time = time;
    }

    @Override
    /**
     * Overrides toString method.
     * @return String
     */
    public String toString() {
        String status;
        if (this.isDone) {
            status = "[✓]";
        } else {
            status = "[✗]";
        }
        return String.format("[E]%s %s (at: %s)", status, this.name, this.time);
    }
}
