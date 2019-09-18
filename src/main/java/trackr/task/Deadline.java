package trackr.task;

/**
 * Represents a task to be completed by a certain date. A <code>Deadline</code> object
 * corresponds to a task represented by a name, type and date.
 */
public class Deadline extends Task {

    /**
     * Type of task.
     */
    String type;

    /**
     * Date of deadline.
     */
    String date;

    /**
     * Class constructor assigning name, type and date to the object.
     * @param taskName The name of the deadline task
     * @param date The date the task is due
     */
    public Deadline(String taskName, String date) {
        super(taskName);
        type = "deadline";
        this.date = date;
    }

    /**
     * Constructor that creates a new Deadline object using a Task object.
     * @param t Task object
     */
    public Deadline(Task t) {
        super(t.toString());
        this.type = "deadline";
        this.date = t.getDate();
        this.isDone = t.getStatus();
    }

    /**
     * This method is used to get the type of the deadline task.
     * @return String This returns the type of the deadline task
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * This method is used to get the string representing the deadline task.
     * @return String This returns the string representing the deadline task
     */
    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    /**
     * This method is used to get the date of the deadline task.
     * @return String This returns the string representing the date of the deadline
     */
    @Override
    public String getDate() {
        return date;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Deadline d = (Deadline) super.clone();
        return d;
    }
}
