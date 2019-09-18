package task;

import java.util.Date;
import formatter.TimeFormatter;

/**
 * Represents a task with a deadline <param>timeSlot</param> as a Date
 */

public class EventTask extends Task {
    //Add variable for eventTask
    Date timeSlot;

    /**
     * Sets the initial params for the task and sets type to E for event
     *
     * @param taskInput  String of the actual task
     * @param complete Boolean is initially set to false
     * @param timing Date is the date and time that this task should be completed within
     */

    public EventTask(String taskInput, boolean complete, Date timing) {
        super(taskInput,complete);
        type = "E";
        timeSlot = timing;
    }

    /**
     *
     */

    public Date getTime() {
        return timeSlot;
    }

    /**
     *
     */

    @Override
    public String toString() {
        if(completed) {
            return "[" + type  + "]" + "[\u2713] " + name + "(at: " + TimeFormatter.convertToString(timeSlot) + ")";
        } else {
            return "[" + type + "]" + "[\u2718] " + name + "(at: " + TimeFormatter.convertToString(timeSlot) + ")";
        }
    }
}
