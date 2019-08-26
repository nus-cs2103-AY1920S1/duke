/**
 * Represents a Task with type Deadline
 */
public class Deadline extends Task {
    private DateTime dateTime;

    Deadline(String name, String dateTime) throws DukeException {
        super(name);
        //String[] arr = dateTime.trim().split(" ", 2);
        this.dateTime = new DateTime(dateTime.trim());
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