// @@author CS2103/T Software Engineering AY1920S1
// Referenced from https://nus-cs2103-ay1920s1.github.io/website/schedule/week2/project.html
// with minor modifications 

import java.util.Date;

/**
 * Class representation of a Date in the list.
 */
public class Deadline extends Task {
    protected Date by;

    /**
     * Creates a Deadline with a description and a Date by which it must be done.
     * 
     * @param description A String description of this Deadline
     * @param by Date by which this Deadline must be completed
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String representation of this Deadline.
     * 
     * @return String representation of this Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Get the date by which this Deadline must be done.
     * 
     * @return Date by which this Deadline must be done
     */
    public Date getBy() {
        return this.by;
    }
}