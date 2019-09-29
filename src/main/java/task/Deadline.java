package task;

import datetime.DateTime;
import exception.DukeException;

/**
 * Deadline task object. Has an task name, date and time.
 */
public class Deadline extends Recurrence {
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
     * Constructor used for generating next recurrence of Deadline.
     * @param taskName name of event.
     * @param dateTime next session timing.
     */
    public Deadline(String taskName, DateTime dateTime){
        super(taskName);
        this.dateTime = dateTime;
    }

    /**
     * Generates uncompleted clone of task.
     * @return new task.
     * @throws DukeException
     */
    public Task getRecurrence() throws DukeException{
        assert(super.isRecurring);
        DateTime newDateTime = this.dateTime.setNewDate(super.unitTime, super.quantity);
        Deadline newTask = new Deadline(super.description, newDateTime);
        newTask.isDone = false;
        newTask.setAsRecurring(unitTime, quantity);
        return newTask;
    }

    /**
     * toString method of DeadLine.
     * @return String denoting task name, status date and time of Deadline task.
     */
    @Override
    public String toString() {
        String output = "[D][" + super.getStatusIcon() + "]" + " " + super.description
                + System.lineSeparator() + dateTime.toString();
        if (super.isRecurring) {
            output += System.lineSeparator()
                +  "recurring every:" + System.lineSeparator()
                + super.quantity + " " + super.unitTime + "(s)";
        }
        return output;
    }

    /**
     * How task data will be stored as a string in the txt file
     * @return task data formatted as String.
     */
    public String parse() {
        String output = "[D][" + super.getStatusIcon() + "]" + " " + super.description
                + "(by: " + dateTime.toString() + ")";
        if (super.isRecurring) {
            output += " [recurring every: " + super.quantity + " " + super.unitTime + "(s)]";
        }
        return output;
    }
}
