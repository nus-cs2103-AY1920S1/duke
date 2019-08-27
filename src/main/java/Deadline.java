import java.util.Date;

public class Deadline extends Task {
    protected Date deadlineDate;

    public Deadline(String description, String deadlineDateString) throws DukeException {
        super(description);
        this.deadlineDate = DateManager.stringToDate(deadlineDateString);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateManager.dateToString(deadlineDate) + ")";
    }

    @Override
    public String getSaveString() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + by;
    }
}
