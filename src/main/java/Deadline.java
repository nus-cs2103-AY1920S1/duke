/**
 * Represents a Task with type Deadline
 */
public class Deadline extends Task {
    private DateTime dateTime;

    Deadline(String name, DateTime dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    Deadline(String name, boolean isComplete, DateTime dateTime) {
        super(name, isComplete);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime + ")";
    }

    @Override
    String publishTask() {
        return "D | " + super.publishTask() + " | " + this.dateTime;
    }
}