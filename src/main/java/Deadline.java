public class Deadline extends Task {

    protected String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public Deadline(Status status, String taskName, String deadline) {
        super(status, taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")" + "\n";
    }

    public String toSaveString() {
        return "D|" + (super.completed == Status.INCOMPLETE ? "0" : "1") + "|" + taskName + "|" + deadline;
    }
}
