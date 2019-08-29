/**
 * Represents a task of the type DeadLine.
 */
public class Deadline extends Task {

    /**
     * Creates a new instance of DeadLine labeled "D".
     *
     * @param s description of the task.
     * @param t time of the task.
     */
    public Deadline(String s, String t) {
        super(s, t);
        this.label = "D";
    }

    /**
     * Returns a line with details of the DeadLine task.
     *
     * @return String representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.label + "]" + this.getStatusIcon() + this.description + " (by: " + this.time + ")";
    }
}
