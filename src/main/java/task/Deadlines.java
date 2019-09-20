package task;

import main.TaskList;

import java.time.LocalDateTime;

/**
 * Represents the task deadline.
 */
public class Deadlines extends DateTask {

    /**
     * Creates the Deadlines object.
     *
     * @param description Task message.
     * @param date        Date of deadline in DD/MM/YYYY TTTT format.
     * @param isDone      Marks the task as done nor not done.
     */
    public Deadlines(String description, LocalDateTime date, boolean isDone) {
        super(description, date, isDone);
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription()
                + " (by: " + TaskList.localDateTimeToString(getDate()) + ")";
    }
}
