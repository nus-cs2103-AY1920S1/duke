package SerSlacksalot.task;

import SerSlacksalot.exception.DukeException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an event to be completed.
 * Contains a description of the task.
 * Contains the time of the event.
 */
public class Event extends Task {
    protected String time;
    protected Date date;

    public Event(String descriptionAndTime) {
        super(descriptionAndTime);
        this.type = "E";
        String[] splitString = descriptionAndTime.split("/at");
        this.description = splitString[0].substring(1, splitString[0].length() - 1);
        this.time = splitString[1].substring(1);
    }

    /**
     * Determines date from time input if format fits "DD/MM/YYYY HHMM".
     *
     * @throws Exception If date is unable to be understood or parsed.
     */
    @Override
    public void understandDate() throws Exception {
        String[] splitString = this.time.split(" ");
        //If time input is split exactly into date and time
        if (splitString.length == 2) {
            //If time input has exactly 4 digits
            if (splitString[1].length() == 4) {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(splitString[0]);
                long hour = Long.parseLong(splitString[1].substring(0, 2));
                long min = Long.parseLong(splitString[1].substring(2, 4));
                date.setTime(date.getTime() + ((hour * 60 + min) * 60000));
                this.time = date.toString();
            } else {
                throw new DukeException("Time is not in the correct format");
            }
        } else {
            throw new DukeException("Date is not in the format: (DD/MM/YYYY HHMM)");
        }
    }

    /**
     * Returns the time of the task.Task.
     *
     * @return timeToGet String containing the time of the task.Task.
     */
    @Override
    public String getTime() {
        String timeToGet = (" /at " + this.time);
        return timeToGet;
    }

    @Override
    public String toString() {
        String output = "[E][" + this.getStatusIcon() + "] " + this.description;
        output += " (at: " + this.time + ")";
        return output;
    }
}
