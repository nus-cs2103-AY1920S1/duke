package task;

import datetime.DateTime;
import exception.DukeException;

/**
 * Event task object. Has an task name, date, starting time and ending time.
 */
public class Event extends Task{
    DateTime date_Time;

    /**
     * Constructor for Event object. Called when generating TaskList based on user input.
     * @param description contains task name, date, starting time and ending time.
     * @throws DukeException thrown when description format is incorrect. or when setEventTime throws DukeException.
     */
    public Event(String description) throws DukeException{
        super(description);
        int divider = description.indexOf("/at");
        if (divider == -1 || (divider == description.length() - 3)
                ||description.substring(divider + 4).replace(" ", "").equals("")){
            throw new DukeException("Incorrect Event format" + System.lineSeparator()
                    + "    Please key in Event (taskname) /by date(d/MM/yyyy) start_time(HHmm)-end_time(HHmm)");
        }
        date_Time = DateTime.setEventTime(description.substring(divider + 4, description.length()));
        super.description = super.description.substring(0, divider);
    }


    /**
     * toString method of Event
     * @return String denoting task name, status, date, and starting and ending time of task.
     */
    @Override
    public String toString(){
        String output = "[E][" + super.getStatusIcon() + "]" + " " + super.description + "(at: "
                + date_Time.toString() + ")";
        return output;
    }
}
