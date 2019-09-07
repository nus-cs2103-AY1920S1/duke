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
     *
     * @param description The task description.
     * @param at The date and time it is due.
     * @throws DukeException If the input format is incorrect.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;

        try {
            int[] dateTimeArray = getDateTimeArray(at);
            date = dateTimeArray[0];
            month = dateTimeArray[1];
            year = dateTimeArray[2];
            time = dateTimeArray[3];
            this.at = formatDateAndTime(year, month, date, time);
        } catch (DukeException e) {
            throw new DukeException("OOPS!!! Wrong input format.\n"
                    + "\"Event <description> /at <DD/MM/YYYY> <XX:XX>\"\n");
        }
    }

    /**
     * Gets the description of the Event task.
     *
     * @return The description of the Event task.
     */
    @Override
    public String getDescription() {
        return super.description + " | " + at;
    }

    /**
     * The proper representation of the task, with [E], icon and description.
     *
     * @return The output representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}