/**
 * Represents a task of the type Deadline
 */
public class Deadline extends Task {
    private String timeOfDeadline;

    /**
     * Creates a new instance of Deadline task denoted as 'D'.
     *
     * @param s description of the task.
     * @param time time of the task by which it needs to be completed.
     */
    public Deadline(String s, String time) {
        super(s); //Call to Parent Class
        this.timeOfDeadline = time;
    }

    /**
     * Returns a line with details.
     *
     * @return a String containing details of the task.
     */

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + this.description + " (by: " + this.timeOfDeadline + ")";
    }
}

