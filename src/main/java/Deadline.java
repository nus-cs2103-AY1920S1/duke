import java.text.SimpleDateFormat;
import java.util.Date;

/** Task to represent a deadline. */
class Deadline extends Task {

    protected Date date;

    public Deadline(String name, Date date) {
        super(name);
        this.date = date;
    }

    // get task type
    public TaskType getType() {
        return TaskType.DEADLINE;
    }

    // get task date
    public Date getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        String doneStr = this.done ? "✓" : "✗";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return String.format("[D][%s] %s (by: %s)", doneStr, this.name, format.format(this.date));
    }
}