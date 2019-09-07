package duke.task;

import duke.exception.DukeException;

public class Deadline extends Task {

    protected String by;

    protected int date;
    protected int month;
    protected int year;
    protected int time;

    /**
     * Constructor to create the Deadline object.
     *
     * @param description The task description.
     * @param by The date and time it is due.
     * @throws DukeException If the input format is incorrect.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;

        try {
            int[] dateTimeArray = getDateTimeArray(by);
            date = dateTimeArray[0];
            month = dateTimeArray[1];
            year = dateTimeArray[2];
            time = dateTimeArray[3];
            this.by = formatDateAndTime(year, month, date, time);
        } catch (DukeException e) {
            throw new DukeException("OOPS!!! Wrong input format.\n"
                    + "\"Deadline <description> /at <DD/MM/YYYY> <XX:XX>\"\n");
        }
    }

    /**
     * Empty constructor.
     */
    public Deadline() {
    }

    /**
     * Helper method for the string in storage format.
     *
     * @param description Description of the Deadline.
     * @param by Date and time of deadline.
     * @return Deadline object.
     */
    public static Deadline storedDeadline(String description, String by) {
        Deadline deadline = new Deadline();
        deadline.description = description;
        deadline.by = by;
        return deadline;
    }

    /**
     * To get string with type and description for duplication check.
     *
     * @return String to check for duplicates.
     */
    public String getDuplicateCheckString() {
        return String.format("D %s", description);
    }

    /**
     * Gets the description of the Deadline task.
     *
     * @return The description of the Deadline task.
     */
    @Override
    public String getDescription() {
        return super.description + " | " + by;
    }

    /**
     * The proper representation of the task, with [D], icon and description.
     *
     * @return The output representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}