package seedu.duke.tasks;

public class DeadlineTask extends Task {
    private static final long serialVersionUID = -2528903469087431L;
    private final String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s%s", super.toString(),
                deadline != null && !deadline.isBlank()
                        ? String.format(" (by: %s)", deadline)
                        : ""
                );
    }
}
