import java.util.Date;

public class Event extends Task {
    private DateTime at;

    public Event(int taskNumber, String taskCheck, String taskName, String type, DateTime t) {
        super(taskNumber, taskCheck, taskName, type);
        at = t;
    }

    public DateTime getAB() {
        return at;
    }

    @Override
    public String toString() {
        return Integer.toString(getTaskNumber()) + ".[E]" + getTaskCheck() + getTaskName() + "at " + at;
    }
}
