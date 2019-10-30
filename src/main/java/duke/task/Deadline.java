package duke.task;

/**
 * The Deadline class represents any tasks that has a
 * description, and a deadline in nature.
 */
public class Deadline extends Task {
    private String date;
    private DateTime dateTime;

    /**
     * Creates a Deadline object, which is also a Task.
     * @param description a description of the Deadline Task
     * @param date the date of the Deadline which is to be done by
     */
    public Deadline(String description, String date) {
        super(description);
        if (!date.contains("of")) { // only formatted dates contain "of"
            this.dateTime = new DateTime(date);
            this.date = this.dateTime.getDateTimeString();
        } else {
            // since it is already formatted, no need to parse DateTime
            this.date = date;
        }
    }

    /**
     * Returns a string representation of a Deadline object.
     * @return a string representation of a Deadline object
     */
    public String toString() {
        return String.format("[D][%s] %s(by: %s)",
                    this.getStatusIcon(), this.description, this.date);
    }

    /**
     * Returns a string representation of the Event object to be saved
     * into the hard disk file for the Duke program.
     * @return the data representation of the Event Task
     */
    public String toData() {
        return String.format("D | %s | %s | %s",
                this.getStatusIcon(), this.description, this.date);
    }

    /**
     * Returns a string representation of the expected
     * Deadline in the new format.
     * @return a string of the deadline's date
     */
    public String getDeadline() {
         return this.date;
    }
}
