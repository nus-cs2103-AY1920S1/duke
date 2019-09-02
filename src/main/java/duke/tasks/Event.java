package duke.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents the task with event specifications.
 */
public class Event extends Task {

    protected Date at;

    /**
     * Creates an initial event task that initializes
     * to incomplete by default.
     *
     * @param description a description given for the task
     * @param at the actual deadline for the deadline task
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * Creates a event task mainly for storage purposes.
     * This constructor keeps the state of the task whether
     * is it complete or incomplete.
     *
     * @param description a description given for the task
     * @param at the date and time which the task needs to be
     *           completed by
     * @param doner the completion status of the task
     *              (Completed is 1, incomplete is 2)
     */
    public Event(String description, Date at, int doner) {
        super(description);
        if (doner == 1) {
            super.completed();
        }
        this.at = at;
    }

    /**
     * Returns a string that represents the event that will be stored in
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
        return "E|" + a + "|" +  super.description + "|" + formatter.format(at);
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        return "[E]" + super.toString() + "(at: " + formatter.format(at) + ")";
    }
}