package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    private LocalDateTime when;

    /**
     * Creates an Event task with the associated description and event day and time.
     * @param desc Description of the event.
     * @param when The event day and time.
     */
    public Event(String desc, String when) {
        super(desc);
        this.when = convertStringToDateTime(when);
    }

    /**
     * Creates an Event task with the specified description, isDone status and event day and time.
     * @param desc Description of the event.
     * @param isDone if true, the Event task is done.
     * @param when The event day and time.
     */
    public Event(String desc, boolean isDone, String when) {
        super(desc, isDone);
        this.when = convertStringToDateTime(when);
    }

    public LocalDateTime getWhen() {
        return this.when;
    }

    public void setWhen(String when) {
        this.when = convertStringToDateTime(when);
    }

    /**
     * Converts the specified string to a LocalDateTime object.
     * @param when The specified event date and time to be converted.
     * @return The LocalDateTime of the specified event date and time.
     */
    private LocalDateTime convertStringToDateTime(String when) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
        return LocalDateTime.parse(when, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
        return String.format("[%s][%s] %s (at: %s)", "E", super.getDoneSymbol(), this.desc, this.when.format(formatter));
    }
}
