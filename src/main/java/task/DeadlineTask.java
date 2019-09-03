package task;
import java.util.Date;
import formatter.TimeFormatter;

/**
 *
 */

public class DeadlineTask extends Task {
    //Add variable for deadline tasks
    Date deadline;

    /**
     *
     */

    public DeadlineTask(String inputTask, boolean complete, Date endTime) {
        super(inputTask,complete);
        type = "D";
        deadline = endTime;
    }

    /**
     *
     */

    public Date getTime() {
        return deadline;
    }

    /**
     *
     */

    @Override
    public String toString() {
        if(completed) {
            return "[" + type  + "]" + "[\u2713] " + name + "(by: " + TimeFormatter.convertToString(deadline) + ")";
        } else {
            return "[" + type + "]" + "[\u2718] " + name + "(by: " + TimeFormatter.convertToString(deadline) + ")";
        }
    }


}
