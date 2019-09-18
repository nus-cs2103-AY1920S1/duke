package task;

import java.util.Date;
import formatter.TimeFormatter;

/**
 * Represents a task with a deadline <param>deadline</param> as a Date
 */

public class DeadlineTask extends Task {
    //Add variable for deadline tasks
    Date deadline;

    /**
     * Sets the initial params for the task and sets type to D for deadline
     *
     * @param inputTask  String of the actual task
     * @param complete Boolean is initially set to false
     * @param endTime Date is the date and time that this task should be completed by
     */

    public DeadlineTask(String inputTask, boolean complete, Date endTime) {
        super(inputTask,complete);
        type = "D";
        deadline = endTime;
    }

    public Date getTime() {
        return deadline;
    }


    @Override
    public String toString() {
        if(completed) {
            return "[" + type  + "]" + "[\u2713] " + name + "(by: " + TimeFormatter.convertToString(deadline) + ")";
        } else {
            return "[" + type + "]" + "[\u2718] " + name + "(by: " + TimeFormatter.convertToString(deadline) + ")";
        }
    }


}
