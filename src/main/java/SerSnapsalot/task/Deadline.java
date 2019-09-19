package SerSnapsalot.task;

import SerSnapsalot.exception.DukeException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a task with a deadline.
 * Contains a description of the task.
 * Contains the time of the deadline.
 */
public class Deadline extends Task {
    protected String time;


    public Deadline(String descriptionAndTime) {
        super(descriptionAndTime);
        this.type = "D";
        String[] splitString = descriptionAndTime.split("/by");
        this.description = splitString[0].substring(1, splitString[0].length() - 1);
        this.time = splitString[1].substring(1);
    }

    /**
     * Returns the time of the task.Task.
     *
     * @return timeToGet String containing the time of the task.Task.
     */
    @Override
    public String getTime() {
        return (" /by " + this.time);
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
        String myDate = splitString[0];
        String myTime = splitString[1];
        if (splitString.length == 2) {
            //If time input has exactly 4 digits
            if (myTime.length() == 4) {
                Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(this.time);
                this.time = date.toString();
            } else {
                throw new DukeException("Time is not in the correct format");
            }
        } else {
            throw new DukeException("Date is not in the format: (DD/MM/YYYY HHMM)");
        }
    }

    @Override
    public String toString() {
        String output = "[D][" + this.getStatusIcon() + "] " + this.description;
        output += " (by:\n" + this.time + ")";
        return output;
    }
}
