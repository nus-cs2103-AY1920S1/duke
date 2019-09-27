package task;

import java.util.Date;
import formatter.TimeFormatter;

/**
 * Represents a task with a deadline timeSlot as a Date.
 */

public class EventTask extends Task {
    //Add variable for eventTask
    Date start;
    Date end;

    /**
     * Sets the initial params for the task and sets type to E for event.
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
     * Overloaded constructor for reading from file.
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
        completed = c;
        start = s;
        end  = e;
    }



    public Date getStartTime() {
        return start;
    }

    public Date getEndTime() {
        return end;
    }


    @Override
    public String toString() {
        if (completed) {
            return "[" + type  + "]" + "[" + tick + "] " + name
                    + "(FROM: " + TimeFormatter.convertToStringPrint(start)
                    + " TO: " + TimeFormatter.convertToStringPrint(end) + ")";
        } else {
            return "[" + type  + "]" + "[" + cross + "] " + name
                    + "(FROM: " + TimeFormatter.convertToStringPrint(start)
                    + " TO: " + TimeFormatter.convertToStringPrint(end) + ")";
        }
    }
}
