package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class. Provides a framework for new Event objects.
 */
public class Event extends Task {

    /** Stores the date and time of the deadline. */
    protected LocalDateTime time;
    /** Stores the formatter by which the date and time is printed. */
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Creates a new Event object.
     * @param taskName The name of the Event task.
     * @param time The due date and time of the Event task.
     */
    public Event(String taskName, LocalDateTime time) {
        super(taskName);
        this.time = time;
    }

    /**
     * Creates a new Event object with its done status pre-determined.
     * @param status The status of the Event object.
     * @param taskName The name of the Event task.
     * @param time The due date and time of the Event task.
     */
    public Event(Status status, String taskName, LocalDateTime time) {
        super(status, taskName);
        this.time = time;
    }

    /**
     * Returns a string describing the Event task.
     * @return A string describing the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(formatter) + ")" + "\n";
    }

    /**
     * Returns a string in the format to be saved to disk.
     * @return A string in the format to be saved to disk.
     */
    public String toSaveString() {
        return "E|" + (super.completed == Status.INCOMPLETE ? "0" : "1") + "|" + taskName + "|"
                + time.format(formatter);
    }

}
