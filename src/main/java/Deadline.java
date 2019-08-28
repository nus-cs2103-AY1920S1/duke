//package mypackage;

/**
 * Represents the deadline task.
 */
public class Deadline extends Task {

    /**
     * The date of the deadline.
     */
    protected Date date;

    /**
     * Constructs the deadline task.
     * @param description description of the task
     * @param by deadline
     */
    public Deadline(String description, String by) {
        super(description);
        String[] dateAndTime = new String[2];
        dateAndTime = by.split(" ");
        String[] date = new String[3];
        date = dateAndTime[0].split("/");
        this.date = new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]),
                            Integer.parseInt(date[2]), Integer.parseInt(dateAndTime[1]));
    }

    /**
     * Gets the deadline.
     * @return deadline
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the string representation of the task.
     * @return task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}