package duke.task;

/**
 * The Event class extends Task. It is a type of task with a specified location.
 */
public class Event extends Task {
    /**
     * The location of the task.
     */
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}
