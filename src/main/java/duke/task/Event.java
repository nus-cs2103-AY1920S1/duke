package duke.task;

import duke.storage.Storage;

import java.time.LocalDateTime;

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
        new Storage();
        this.when = Storage.convertStringToDateTime(when);
    }

    /**
     * Creates an Event task with the specified description, isDone status and event day and time.
     * @param desc Description of the event.
     * @param isDone if true, the Event task is done.
     * @param when The event day and time.
     */
    public Event(String desc, boolean isDone, String when) {
        super(desc, isDone);
        this.when = Storage.convertStringToDateTime(when);
    }

    public LocalDateTime getWhen() {
        return this.when;
    }

    public void setWhen(String when) {
        this.when = Storage.convertStringToDateTime(when);
    }

    @Override
    public String toString() {
        String when = Storage.convertDateTimeToString(this.when);
        return String.format("[%s][%s] %s (at: %s)", "E", super.getDoneSymbol(), this.desc, when);
    }
}
