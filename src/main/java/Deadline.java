public class Deadline extends Task {
    private String deadline;

    public Deadline(int index, String topic, String deadline) {
        super(index, topic);
        this.deadline = deadline;
        this.type = "D";
        this.details = String.format("%s (by: %s)", topic, deadline);
    }
}
