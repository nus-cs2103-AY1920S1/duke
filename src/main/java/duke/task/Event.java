package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a duke.task.Task with a time of event attached
 */

public class Event extends TimeTask {
    private String time;

    public Event(String name, boolean isDone, String type,  String time, LocalDateTime localDateTime) {
        super(name, isDone, type, localDateTime);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getName() + " (at: " + getLocalDateTime().toString()  + ")";
    }
}
