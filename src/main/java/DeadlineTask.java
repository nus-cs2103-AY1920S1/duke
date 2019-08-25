public class DeadlineTask extends Task {

    String deadline;

    public DeadlineTask(String details, String deadline) {
        super(details);
        this.deadline = deadline;
    }

    @Override
    protected String toFileString() {
        int done = isDone ? 1 : 0;
        return "D" + " | " + done + " | " + details + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
