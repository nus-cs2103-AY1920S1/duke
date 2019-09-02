package task;

import datetime.DateTime;
import exception.DukeException;

/**
 * Deadline task object. Has an task name, date and time.
 */
public class Deadline extends Task {
    DateTime dateTime;

    /**
     * Constructor for Deadline object. Called when generating TaskList based on user input.
     * @param description includes task name, date and time.
     * @throws DukeException thrown when description format is incorrect. or when setDeadline throws DukeException.
     */
    public Deadline(String description) throws DukeException {
        super(description);
        int divider = description.indexOf("/by");
        if (divider == -1 || (divider == description.length() - 3)
                || description.substring(divider + 4).replace(" ", "").equals("")) {
            throw new DukeException("Incorrect Deadline format." + System.lineSeparator()
                    + "    Please key in Deadline (taskname) /by date(d/MM/yyyy) time(HHmm)");
        }
        dateTime = DateTime.setDeadline(description.substring(divider + 4, description.length()));
        super.description = super.description.substring(0, divider);
    }


    /**
     * toString method of DeadLine.
     * @return String denoting task name, status date and time of Deadline task.
     */
    @Override
    public String toString() {
        String output = "[D][" + super.getStatusIcon() + "]" + " " + super.description
                + "(by: " + dateTime.toString() + ")";
        return output;
    }
}
