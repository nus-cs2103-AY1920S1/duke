package task;

import main.TaskList;

import java.time.LocalDateTime;

/**
 * Represents the task event.
 */
public class Events extends Task {

    private LocalDateTime date;

    /**
     * Creates the Events object
     *
     * @param description Task message.
     * @param date        Date of deadline in DD/MM/YYYY TTTT format.
     * @param isDone      Marks the task as done nor not done.
     */
    public Events(String description, LocalDateTime date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    /**
     * Returns the DateTime object of Events.
     *
     * @return DateTime object of events.
     */
    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription() + " (at: " + TaskList.localDateTimeToString(this.date)+ ")";
    }
}
