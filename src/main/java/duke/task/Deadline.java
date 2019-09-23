package duke.task;

/**
 * Class that represents a deadline task.
 */
public class Deadline extends Task {

    /**
     * Constructor that takes in the description and the date and time of the deadline.
     * @param description The main message of the deadline.
     * @param extraInfo The date and time of the deadline in exact format.
     */
    public Deadline(String description, String extraInfo) {
        super(description);
        this.extraInfo = extraInfo;
        this.type = "deadline";
    }

    @Override
    public String toString() {
        return "[ D ]" + super.toString() + " (by: " + this.extraInfo + ")";
    }
}
