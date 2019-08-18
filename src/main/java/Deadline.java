public class Deadline extends Task {
    private String deadline;

    public Deadline(String topic, String deadline) {
        super(topic);
        this.deadline = deadline;
        this.type = "D";
        this.details = String.format("%s (by: %s)", topic, deadline);
    }
}
