package duke.task;

/**
 * Encapsulates a task object of type ToDo.
 */
public class ToDo extends Task {
    /**
     * Construct an ToDo object.
     *
     * @param topic the topic of the ToDo.
     */
    public ToDo(String topic) {
        super(topic);
        this.type = "T";
        this.details = topic;
        this.detailsForDatabase = topic;
    }
}
