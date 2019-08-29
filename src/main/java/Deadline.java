import java.text.SimpleDateFormat;
import java.util.Date;

/** Task to represent a deadline. */
class Deadline extends Task {

    protected Date date;

    /**
     * Class constructor for Deadline.
     * @param name Description of the deadline.
     * @param date Date of the deadline.
     */
    public Deadline(String name, Date date) {
        super(name);
        this.date = date;
    }

    /**
     * Get the task type of the task.
     * @return DEADLINE value of the enum TaskType.
     */
    public TaskType getType() {
        return TaskType.DEADLINE;
    }

    /**
     * Get the date of deadline.
     * @return Date of deadline.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * String method to override the toString() in java.lang.Object.
     * @return String representation of the object.
     */
    @Override
    public String toString() {
        String doneStr = this.done ? "✓" : "✗";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return String.format("[D][%s] %s (by: %s)", doneStr, this.name, format.format(this.date));
    }
}