public class Deadline extends Task {

    private String dueDateTime;

    public Deadline(String description, String dueDateTime) {
        super(description);
        this.dueDateTime = dueDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDateTime + ")";
    }
}
