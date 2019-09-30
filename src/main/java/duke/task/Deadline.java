package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a duke.task.Task with a deadline time attached
 */

public class Deadline extends TimeTask {
    private String time;


    public Deadline(String name, boolean isDone, String type, String time, LocalDateTime localDateTime) {
        super(name, isDone, type, localDateTime);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getName() + " (by: " + getLocalDateTime().toString()  + ")";    }
}
