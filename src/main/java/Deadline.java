/**
 * Represents a Task with type Deadline
 */
public class Deadline extends Task {
    private String dateTime;

    Deadline(String name, String dateTime) {
        super(name);
        String[] arr = dateTime.split(" ", 2);
        this.dateTime = arr[1];
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