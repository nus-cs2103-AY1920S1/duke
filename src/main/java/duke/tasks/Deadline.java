package duke.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents the task with deadline specifications.
 */
public class Deadline extends Task {

    protected Date by;

    /**
     * Create an initial deadline task that initializes
     * to incomplete by default.
     * @param description a description given for the task
     * @param by the actual deadline for the deadline task
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a deadline task mainly for storage purposes.
     * This constructor keeps the state of the task whether
     * is it done or incomplete.
     *
     * @param description a description given for the task
     * @param by the date and time which the task needs to be
     *           completed by
     * @param doner the completion status of the task
     *              (Completed is 1, incomplete is 2)
     */
    public Deadline(String description, Date by, int doner) {
        super(description);
        if (doner == 1) {
            super.completed();
        }
        this.by = by;
    }

    /**
     * Returns a string that represents the deadline that will be stored in
     * the format got storage and retrieval in a .txt file.
     *
     * @return the string of the task that will be saved in the .txt file
     */
    @Override
    public String save() {
        int a = 0;
        if (super.isDone) {
            a = 1;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy HHmm");
        return "D|" + a + "|" + super.description + "|" + formatter.format(by);
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        return "[D]" + super.toString() + "(by: " + formatter.format(by) + ")";
    }
}