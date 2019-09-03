package task;

import java.util.Date;
import formatter.TimeFormatter;

/**
 *
 */

public class EventTask extends Task {
    //Add variable for eventTask
    Date timeSlot;

    /**
     *
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
