package duke.task;

/**
 * Deadline class.
 */
public class Deadline extends Task {
    private String date;


    /**
     * Constructor for Deadline object.
     *
     * @param n name of task
     * @param date date of task
     */
    public Deadline(String n, String date) {
        super(n);
        this.date = date;
    }


    /**
     * Constructor for Deadline object when loading from history.
     *
     * @param n name of task
     * @param date date of task
     * @param completed indicates if task is completed
     */
    public Deadline(String n, String date, boolean completed) {
        super(n, completed);
        this.date = date;
    }

    /**
     * Returns date of task.
     *
     * @return date of task
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns string representation of Deadline object.
     *
     * @return String representation of Deadline object
     */
    @Override
    public String toString() {
        String result = "[D][";
        result += super.toString();
        result += " (by: " + this.date + ")";
        return result;
    }
}
