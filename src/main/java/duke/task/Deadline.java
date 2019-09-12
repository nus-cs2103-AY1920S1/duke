package duke.task;

import java.util.Date;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    Date deadline;

    /**
     * Initialize a deadline task with content and the deadline of task.
     * @param content The String description of the Deadline.
     * @param deadline A Date object that represents the deadline.
     */
    public Deadline(String content, Date deadline) {
        super(content);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline in a dd/mm/yyyy HHmm format.
     * @return The String format of the deadline.
     */
    @Override
    public String getTime() {
        return inputFormatter.format(deadline);
    }

    /**
     * Reschedule the date of the event.
     * @param date Date object
     */
    @Override
    public void reschedule(Date date) {
        this.deadline = date;
    }

    /**
     * Format the task into a format presentable to users.
     * @return The format that the user wish to see.
     */
    @Override
    public String toString() {
        return done ? String.format("[D][%c] %s (by: %s)", tick, content, outputFormatter.format(deadline))
                : String.format("[D][%c] %s (by: %s)", cross, content, outputFormatter.format(deadline));
    }
}