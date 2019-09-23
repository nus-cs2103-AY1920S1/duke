package duke.task;

import duke.date.DateUtil;

import java.time.LocalDateTime;

/**
 * A Task object that has two LocalDateTime inputs.
 * The first LocalDateTime represents the date and time when the event starts.
 * The second LocalDateTime represents the date and time when the event ends.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs an unfinished Event object.
     *
     * @param description Description of the task.
     * @param start       Date and time when the event starts.
     * @param end         Date and time when the event ends.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.startTime = start;
        this.endTime = end;
    }

    /**
     * Constructs an Event object.
     *
     * @param description Description of the task.
     * @param start       Date and time when the event starts.
     * @param end         Date and time when the event ends.
     * @param isDone      True if the taks is finished.
     */
    private Event(String description, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(description, isDone);
        this.startTime = start;
        this.endTime = end;
    }

    /**
     * Returns the starting time of this Event object.
     *
     * @return Starting time of this event object.
     */
    @Override
    protected LocalDateTime getTime() {
        return this.startTime;
    }

    /**
     * Marks the task as done.
     *
     * @return New finished task.
     */
    @Override
    public Event finish() {
        return new Event(this.getDescription(), startTime, endTime, true);
    }

    /**
     * ToString method for printing.
     *
     * @return String representation of the event object.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.getDescription()
                + " (from: " + DateUtil.printTime(startTime)
                + ", to " + DateUtil.printTime(endTime) + ")\n";
    }
}
