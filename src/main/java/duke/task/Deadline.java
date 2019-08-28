package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Creates a Deadline task with the specified description and deadline.
     * @param desc Description of the deadline.
     * @param deadline Deadline that the task is due by.
     */
    public Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = stringToDateTime(deadline);
    }

    /**
     * Creates a Deadline task with the specified description, isDone status and deadline.
     * @param desc Deadline that the task is due by.
     * @param isDone if true, the Deadline task is done.
     * @param deadline Deadline that the task is due by.
     */
    public Deadline(String desc, boolean isDone, String deadline) {
        super(desc, isDone);
        this.deadline = stringToDateTime(deadline);
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = stringToDateTime(deadline);
    }

    /**
     * Converts the specified string to a LocalDateTime object.
     * @param deadline The specified deadline to be converted.
     * @return The LocalDateTime of the specified deadline.
     */
    private LocalDateTime stringToDateTime(String deadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return LocalDateTime.parse(deadline, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return String.format("[%s][%s] %s (by: %s)", "D", super.getDoneSymbol(), this.desc, this.deadline.format(formatter));
    }

}
