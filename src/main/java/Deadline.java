/**
 * This class represents Deadline tasks which is a type of Task.
 */
public class Deadline extends Task {
    String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.typeOfTask = "[D]";
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String writeToFile() {
        return super.writeToFile() + "|" + deadline;
    }
}
