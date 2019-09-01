package duke;

import java.util.Date;

class Deadline extends Task {
    private Date by;

    /**
     * Creates a new deadline task.
     * @param description Description of the task
     * @param by Deadline of the task
     */
    Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a new deadline task.
     * @param description Description of the task
     * @param isDone Flag whether task is done
     * @param by Deadline of the task
     */
    Deadline(String description, boolean isDone, Date by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Generates the task's textual representation in save file format.
     * @return Textual representation of the task in save file format
     */
    @Override
    String toSaveFormat() {
        return String.format("D | %s | %s", super.toSaveFormat(), Duke.dateFormatter.format(this.by));
    }

    /**
     * Generates the task's textual representation in display format.
     * @return Textual representation of the task in display format
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Duke.dateFormatter.format(this.by));
    }
}
