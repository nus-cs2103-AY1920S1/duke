package duke.task;

import java.util.Date;

/** Represents a deadline task. */
public class Deadline extends Task {
    private Date date;

    /**
     * Creates a {@link Deadline} that comprises a {@link Date}.
     *
     * @param description Description of the {@link Deadline}
     * @param date {@link Date} before which the task should be completed
     */
    public Deadline(String description, Date date) {
        super(description);
        this.date = date;
    }


    /**
     * Returns the date of the deadline in numerical format.
     *
     * @return A string representation of the date of the deadline.
     */
    public String getDateString() {
        return String.format(
                "%02d/%02d/%04d %02d%02d",
                date.getDate(),
                date.getMonth() + 1,
                date.getYear() + 1900,
                date.getHours(),
                date.getMinutes());
    }

    /**
     * Returns a string formatted to style suitable for {@link duke.util.Storage}.
     *
     * @return String formatted for {@link duke.util.Storage}.
     */
    @Override
    public String getStorageSyntax() {
        int isDoneInteger = super.isDone ? 1 : 0;
        return String.format("D | %d | %s | %s | %d", isDoneInteger,
                this.description, this.getDateString(), this.priority);
    }

    /**
     * Returns a description of the properties of the task.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getDateString());
    }
}
