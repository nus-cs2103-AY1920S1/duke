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
     * @param description The task description.
     * @param by The date and time it is due.
     * @throws DukeException If the input format is incorrect.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;

        try {
            date = Integer.valueOf(by.substring(0, 2));
            month = Integer.valueOf(by.substring(3, 5));
            year = Integer.valueOf(by.substring(6, 10));
            String[] temp = by.split(" ");
            time = Integer.valueOf(temp[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("     ☹ OOPS!!! Wrong input format. \"Deadline <description> /by <DD/MM/YYYY> <XX:XX>\"");
        } catch (NumberFormatException e) {
            throw new DukeException("     ☹ OOPS!!! Wrong input format. \"Deadline <description> /by <DD/MM/YYYY> <XX:XX>\"");
        }
    }

    /**
     * Gets the description of the Deadline task.
     * @return The description of the Deadline task.
     */
    @Override
    public String getDescription() {
        return super.description + " | " + by;
    }

    /**
     * The proper representation of the task, with [D], icon and description.
     * @return The output representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}