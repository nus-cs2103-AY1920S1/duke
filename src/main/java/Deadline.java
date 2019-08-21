public class Deadline extends Task {

    String deadline;

    public Deadline(String details, String deadline) {
        super(details);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "( by: " + deadline + ")";
    }
}
