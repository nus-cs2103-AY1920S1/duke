/**
 * Represents a Task with type Deadline
 */
public class Deadline extends Task {
    private String dateTime;

    Deadline(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        String[] arr = this.dateTime.split(" ", 2);
        return "[D]" + super.toString() + "(" + arr[0] + ": " + arr[1] + ")";
    }
}