package duke.task;

import duke.date.DukeDate;

/**
 * Represents a deadline task inputted by the user.
 */
public class DeadlineTask extends Task {

    /** Stores the date by which the user has to finish this Task. */
    private DukeDate dueDate;

    /**
     * Constructs a DeadlineTask object with the given description and {@link DukeDate}.
     * isDone field is set to false by default.
     *
     * @param description The description of this DeadlineTask.
     * @param dueDate The due date of this DeadlineTask.
     */
    public DeadlineTask(String description, DukeDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Constructs a DeadlineTask object with the given description, status, and {@link DukeDate}.
     *
     * @param description The description of this DeadlineTask.
     * @param isDone The status of this DeadlineTask.
     * @param dueDate The due date of this DeadlineTask.
     */
    public DeadlineTask(String description, boolean isDone, DukeDate dueDate) {
        super(description, isDone);
        this.dueDate = dueDate;
    }

    public DukeDate getDueDate() {
        return this.dueDate;
    }

    /**
     * Returns the dueDate field formatted as a String.
     * The format is "dd Month, YYYY, hh:mm a".
     *
     * @return The dueDate field formatted as a String.
     * @see DukeDate#format(boolean)
     */
    public String getDateAsString() {
        return this.dueDate.format(true);
    }

    /**
     * Returns the type of this DeadlineTask as a single capital letter.
     *
     * @return The letter D.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns the type, status, description, and due date of this DeadlineTask.
     *
     * @return The type, status, description, and due date formatted as a String.
     */
    @Override
    public String getStatus() {
        return String.format("[D]%s (by: %s)",
                             super.getStatus(),
                             this.dueDate.format(true));
    }

}
