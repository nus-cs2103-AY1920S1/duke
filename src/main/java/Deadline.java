public class Deadline extends Task {

    protected String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return taskID + ".[D]" + super.toStringNoID() + " (by: " + deadline + ")" + "\n";
    }

    @Override
    public String toStringNoID() {
        return "[D]" + super.toStringNoID() + " (by: " + deadline + ")" + "\n";
    }
}
