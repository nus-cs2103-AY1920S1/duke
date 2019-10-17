package task;

import main.TaskList;

import java.time.LocalDateTime;

/**
 * Represents the task event.
 */
public class Events extends DateTask {

    /**
     * Creates the Events object.
     *
     * @param description Task message.
     * @param date        Date of deadline in DD/MM/YYYY TTTT format.
     * @param isDone      Marks the task as done nor not done.
     */
    public Events(String description, LocalDateTime date, boolean isDone) {
        super(description, date, isDone);
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription()
                + " (at: " + TaskList.localDateTimeToString(getDate()) + ")";
    }
}
