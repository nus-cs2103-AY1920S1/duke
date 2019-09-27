package task;

import java.util.Date;
import formatter.TimeFormatter;

/**
 * Represents a task with a deadline <param>timeSlot</param> as a Date
 */

public class EventTask extends Task {
    //Add variable for eventTask
    Date start;
    Date end;

    /**
     * Sets the initial params for the task and sets type to E for event
     *
     * @param taskInput  String of the actual task
     * @param s start time
     * @param e end time
     */

    public EventTask(String taskInput, Date s, Date e) {
        super(taskInput);
        type = "E";
        start = s;
        end = e;

    }

    /**
     * Overloaded constructor for reading from file
     *
     * @param i  String of the actual task
     * @param c is the completion status
     * @param s Date is the date and time that this task is starting
     * @param e Date is the date and time that this task is ending
     *
     */

    public EventTask(String i, boolean c, Date s, Date e) {
        super(i);
        type = "E";
        completed= c;
        start= s;
        end  =e;
    }


    /**
     *
     */

    public Date getStartTime() {
        return start;
    }
    public Date getEndTime() { return end;}

    /**
     *
     */

    @Override
    public String toString() {
        if(completed) {
            return "[" + type  + "]" + "[\u2713] " + name +
                    "(from: " + TimeFormatter.convertToString(start) +
                    " to " + TimeFormatter.convertToString(end) + ")";
        } else {
            return "[" + type  + "]" + "[\u2718] " + name +
                    "(from: " + TimeFormatter.convertToString(start) +
                    " to " + TimeFormatter.convertToString(end) + ")";
        }
    }
}
