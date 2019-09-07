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
     * Empty constructor.
     */
    public Event() {
    }

    /**
     * Helper method for the string in storage format.
     *
     * @param description Description of the Event.
     * @param at Date and time of event.
     * @return Event object.
     */
    public static Event storedEvent(String description, String at) {
        Event event = new Event();
        event.description = description;
        event.at = at;
        return event;
    }

    /**
     * To get string with type and description for duplication check.
     *
     * @return String to check for duplicates.
     */
    public String getDuplicateCheckString() {
        return String.format("E %s", description);
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