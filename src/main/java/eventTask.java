import java.util.Date;

public class eventTask extends Task {
    //Add variable for eventTask
    Date timeSlot;

    public eventTask(String taskInput, boolean complete, Date timing) {
        super(taskInput,complete);
        type = "E";
        timeSlot = timing;
    }

    public Date getTime() {
        return timeSlot;
    }

    @Override
    public String toString() {
        if(completed) {
            return "[" + type  + "]" + "[\u2713] " + name + "(at: " + TimeFormatter.convertToString(timeSlot) + ")";
        } else {
            return "[" + type + "]" + "[\u2718] " + name + "(at: " + TimeFormatter.convertToString(timeSlot) + ")";
        }
    }
}
