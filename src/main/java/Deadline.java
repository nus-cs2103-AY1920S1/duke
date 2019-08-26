import java.util.Date;

public class Deadline extends Task {
    private DateTime by;

    public Deadline(int taskNumber, String taskCheck, String taskName, String type, DateTime b) {
        super(taskNumber, taskCheck, taskName, type);
        by = b;
    }

    public DateTime getAB() {
        return by;
    }

    @Override
    public String toString() {
        return Integer.toString(getTaskNumber()) + ".[D]" + getTaskCheck() + getTaskName() + "by "+ by;
    }

}
