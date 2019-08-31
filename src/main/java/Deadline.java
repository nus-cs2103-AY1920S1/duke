public class Deadline extends Task {

    private String deadlineTime;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineTime + ")";
    }
}