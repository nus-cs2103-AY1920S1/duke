package duke.task;

import duke.storage.Storage;

import java.time.LocalDateTime;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    private LocalDateTime dueBy;

    /**
     * Creates a Deadline task with the specified description and due date/time.
     * @param desc describes the deadline.
     * @param dueBy the time/day that the task is due by.
     */
    public Deadline(String desc, String dueBy) {
        super(desc);
        new Storage();
        this.dueBy = (Storage.convertStringToDateTime(dueBy));
    }

    /**
     * Creates a Deadline task with the specified description, isDone status and deadline.
     * @param desc deadline that the task is due by.
     * @param isDone if true, the Deadline task is done.
     * @param dueBy the time/day that the task is due by.
     */
    public Deadline(String desc, boolean isDone, String dueBy) {
        super(desc, isDone);
        new Storage();
        this.dueBy = Storage.convertStringToDateTime(dueBy);
    }

    public LocalDateTime getDueBy() {
        return this.dueBy;
    }

    public void setDueBy(String dueBy) {
        new Storage();
        this.dueBy = Storage.convertStringToDateTime(dueBy);
    }

    @Override
    public String toString() {
        String dueBy = Storage.convertDateTimeToString(this.dueBy);
        return String.format("[%s][%s] %s (by: %s)", "D", super.getDoneSymbol(), this.desc, dueBy);
    }

}
