package duke.task;

import duke.util.DateParser;

import java.time.LocalDateTime;

/**
 * Deadlines are special Tasks that have a due date.
 */
public class Deadline extends Task {

    /** Due date of task */
    private LocalDateTime dueDateTime;

    /**
     * Creates a new Deadline with the given description and due date.
     *
     * @param description       Task to be completed.
     * @param dueDateTime                Due date for the Deadline.
     */
    public Deadline(String description, String dueDateTime) {
        super(description);
        this.dueDateTime = DateParser.parse(dueDateTime);
    }

    /**
     * Creates a new Deadline with the given description, due date and status.
     *
     * @param description       Task to be completed.
     * @param dueDateTime       Due date for the Deadline.
     * @param isDone            Whether the Deadline is completed or not.
     */
    public Deadline(String description, String dueDateTime, boolean isDone) {
        super(description, isDone);
        this.dueDateTime = DateParser.parse(dueDateTime);
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
     * date or time, in an appropriate format for data storage. The due date is
     * formatted according to the default DateTimeFormatter given by DateParser.
     *
     * @return  String representing the current Deadline.
     */
    @Override
    public String formatAsData() {
        return super.formatAsData() + " | "
                + dueDateTime.format(DateParser.getDefaultFormat());
    }

    /**
     * Returns a string containing the type of Task, done status, description,
     * and deadline. The deadline is formatted according to the default
     * DateTimeFormatter given by DateParser.
     *
     * @return  String describing the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + dueDateTime.format(DateParser.getDefaultFormat()) + ")";
    }
}
