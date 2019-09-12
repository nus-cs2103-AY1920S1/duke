package task;

import datetime.DateTime;
import exception.DukeException;

/**
 * Event task object. Has an task name, date, starting time and ending time.
 */
public class Event extends Recurrence {
    DateTime dateTime;

    /**
     * Constructor for Event object. Called when generating TaskList based on user input.
     * @param description contains task name, date, starting time and ending time.
     * @throws DukeException thrown when description format is incorrect. or when setEventTime throws DukeException.
     */
    public Event(String description) throws DukeException {
        super(description);
        int divider = description.indexOf("/at");
        if (divider == -1 || (divider == description.length() - 3)
                || description.substring(divider + 4).replace(" ", "").equals("")) {
            throw new DukeException("Incorrect Event format" + System.lineSeparator()
                    + "    Please key in Event (taskname) /by date(d/MM/yyyy) start_time(HHmm)-end_time(HHmm)");
        }
        dateTime = DateTime.setEventTime(description.substring(divider + 4, description.length()));
        super.description = super.description.substring(0, divider);
    }

    /**
     * Constructor used for generating next recurrence of Event.
     * @param taskName name of event.
     * @param dateTime next session timing.
     */
    public Event(String taskName, DateTime dateTime){
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
        Event newTask = new Event(super.description, newDateTime);
        newTask.isDone = false;
        return newTask;
    }

    /**
     * toString method of Event.
     * @return String denoting task name, status, date, and starting and ending time of task.
     */
    @Override
    public String toString() {
        String output = "[E][" + super.getStatusIcon() + "]" + " " + super.description + "(at: "
                + dateTime.toString() + ")";
        if (super.isRecurring) {
            output += " every: " + super.quantity + " " + super.unitTime + "(s)";
        }
        return output;
    }
}
