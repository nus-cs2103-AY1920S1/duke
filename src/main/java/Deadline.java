/**
 * This class represents Deadline tasks which is a type of Task.
 */
public class Deadline extends Task {
    DateTime deadline;

    /**
     * Constructor for Deadline class.
     * @param description Task description
     * @param deadline a String representing the deadline
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.typeOfTask = "[D]";
        this.deadline = new DateTime(deadline);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline.toString() + ")";
    }

    @Override
    public String writeToFile() {
        return super.writeToFile() + "|" + deadline.getRawForm();
    }
}
