package duke.task;

import duke.exception.DukeException;

public class Event extends Task {

    String at;
    int date;
    int month;
    int year;
    int time;

    /**
     * Constructor to create the Event object.
     * @param description The task description.
     * @param at The date and time it is due.
     * @throws DukeException If the input format is incorrect.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;

        try {
            date = Integer.valueOf(at.substring(0, 2));
            month = Integer.valueOf(at.substring(3, 5));
            year = Integer.valueOf(at.substring(6, 10));
            String[] temp = at.split(" ");
            time = Integer.valueOf(temp[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("     ☹ OOPS!!! Wrong input format. \"Event <description> /at <DD/MM/YYYY> <XX:XX>\"");
        } catch (NumberFormatException e) {
            throw new DukeException("     ☹ OOPS!!! Wrong input format. \"Event <description> /at <DD/MM/YYYY> <XX:XX>\"");
        }
    }

    /**
     * Gets the description of the Event task.
     * @return The description of the Event task.
     */
    @Override
    public String getDescription() {
        return super.description + " | " + at;
    }

    /**
     * The proper representation of the task, with [E], icon and description.
     * @return The output representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}