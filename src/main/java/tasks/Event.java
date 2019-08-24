package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is a class that represents tasks that occurs at a certain time.
 * An Event task, aside from its description and state, stores a LocalDateTime
 * representing the time at which it occurs.
 */
public class Event extends Task {

    /** LocalDateTime storing the time at which the event will occur */
    protected LocalDateTime at;

    /**
     * Event constructor that takes a description, a state isDone
     * as well as a LocalDateTime that describes at which time the event occurs.
     *
     * @param description String containing the description of the task.
     * @param at LocalDateTime detailing when the event occurs.
     * @param isDone boolean storing the task's state of completion.
     */
    public Event(String description, LocalDateTime at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMMM yyyy, ha")) + ")";
}

    /**
     * Returns a string that is of the appropriate format
     * to be saved to the file. This formatting ensures that
     * the task information can be read accurately again in the future.
     * Event objects are to be stored in this format:
     * E | isDone | description d MMMM yyyy ha
     *
     * @return String format of the task to be saved to the file.
     */
    @Override
    public String fileString() {
        return "E" + super.fileString() + " | " + at.format(DateTimeFormatter.ofPattern("d MMMM yyyy, ha"));
    }

}