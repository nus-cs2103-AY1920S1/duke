package duke.task;

import java.util.Date;

/**
 * Represents an event.
 */
public class Event extends Task {
    Date time;

    /**
     * Initialize an event with content and the time of the event.
     * @param content The String description of the Event.
     * @param time A Date object that represents the time of the event.
     */
    public Event(String content, Date time) {
        super(content);
        this.time = time;
    }

    /**
     * Returns the deadline in a dd/mm/yyyy HHmm format.
     * @return The String format of the deadline.
     */
    @Override
    public String getTime() {
        return inputFormatter.format(time);
    }

    /**
     * Reschedule the date of the event.
     * @param date Date object
     */
    @Override
    public void reschedule(Date date) {
        this.time = date;
    }

    /**
     * Format the task into a format presentable to users.
     * @return The format that the user wish to see.
     */
    @Override
    public String toString() {
        return done ? String.format("[E][%c] %s (at: %s)", tick, content, outputFormatter.format(time))
                : String.format("[E][%c] %s (at: %s)", cross, content, outputFormatter.format(time));
    }
}
