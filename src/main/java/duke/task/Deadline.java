package duke.task;

import duke.util.DateParser;

import java.util.Date;

/**
 * Deadlines are special Tasks that have a due date.
 */
public class Deadline extends Task {

    /** Due date of task */
    private Date by;

    /**
     * Creates a new Deadline with the given description and due date.
     *
     * @param description       Task to be completed.
     * @param by                Due date for the Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = DateParser.parse(by);
    }

    /**
     * Creates a new Deadline with the given description, due date and status.
     *
     * @param description       Task to be completed.
     * @param by                Due date for the Deadline.
     * @param isDone            Whether the Deadline is completed or not.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = DateParser.parse(by);
    }

    /**
     * Returns the letter "D", representing the type Deadline.
     *
     * @return  The letter "D" in a String.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns a representation of the current Deadline, including its due
     * date or time, in an appropriate format for data storage.
     *
     * @return  String representing the current Deadline.
     */
    @Override
    public String formatAsData() {
        return super.formatAsData() + " | "
                + String.format("%1$ta, %1$td %1$tb %1$ty, %1$tR", by);
    }

    /**
     * Returns a string containing the type of Task, done status, description,
     * and deadline.
     *
     * @return  String describing the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + String.format("%1$ta, %1$td %1$tb %1$ty, %1$tR", by) + ")";
    }
}
