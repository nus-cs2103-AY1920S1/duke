public class Deadline extends Task {

    private String dueDateTime;

    public Deadline(String description, String dueDateTime) {
        super(description);
        this.dueDateTime = dueDateTime;
    }

    public Deadline(String description, String dueDateTime, boolean isDone) {
        super(description, isDone);
        this.dueDateTime = dueDateTime;
    }

    public String getDueDateTime() {
        return dueDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDateTime + ")";
    }
}
