package task;

import converter.StringDateConverter;

import java.util.Date;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {

    protected Date by;

    /**
     * Initializes Deadline with description and due date.
     *
     * @param description of deadline
     * @param by is the due date of deadline
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Initializes Deadline with description, due date and isDone status.
     *
     * @param description of deadline
     * @param by is the due date of deadline
     * @param isDone true if deadline is completed. Else false
     */
    public Deadline(String description, Date by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public Date getBy() {
        return by;
    }

    public void setBy(Date by) {
        this.by = by;
    }

    @Override
    public String toString() {
        String strDate = new StringDateConverter().convertDateToString(by);
        return "[D]" + super.toString() + " (by: " + strDate + ")";
    }

    /**
     * Converts task object to format use in file save.
     * @return String that is in file save format
     */
    @Override
    public String toSaveFormat() {
        return String.format("D | %s | %s | %s",
                this.isDone ? "Done" : "Not Done",
                this.getDescription(),
                this.getBy());
    }
}