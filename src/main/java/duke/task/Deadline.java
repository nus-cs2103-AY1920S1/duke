package duke.task;

/**
 * Encapsulates a task object of type Deadline.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Construct a Deadline object.
     *
     * @param topic the topic of the deadline.
     * @param deadline its deadline.
     */
    public Deadline(String topic, String deadline) {
        super(topic);
        this.deadline = deadline;
        this.type = "D";
        this.details = String.format("%s (by: %s)", topic, deadline);
        this.detailsForDatabase = String.format("%s | %s", topic, deadline);
    }
}
