package duke.task;

/**
 * This class is ued to represent the task of event type.
 */
public class Event extends Task {

    private String duration;

    /**
     * The constructor of the class, assign the string value to the variables.
     *
     * @param description The description of the task.
     * @param duration    The duration of the task.
     */
    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "E " + super.toString() + " - " + duration;
    }
}
