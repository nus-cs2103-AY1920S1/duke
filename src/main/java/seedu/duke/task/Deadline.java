package seedu.duke.task;

import seedu.duke.parser.DateParser;

import java.util.Date;

/** Represents a task with a deadline. */
public class Deadline extends Task {
    protected String strBy;
    protected Date by;

    /** Constructs a Deadline object. Takes in the description of the task and
     * a string as the deadline of the task.
     * @param description Represents the description of the task.
     * @param strBy Represents the expected date of completion of task.
     */
    public Deadline(String description, String strBy) {
        super(description);
        this.strBy = strBy;
        this.by = new DateParser().understandDate(strBy);
    }

    @Override
    public String writeToFile() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + strBy + ((priority == null)? "\n" : " | "
                + priority + "\n");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + strBy + ")" + ((priority == null)? "" : " <<P: " + priority
                + ">>");
    }
}
