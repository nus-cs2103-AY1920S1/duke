/**
 * Represents a task of the type DeadLine.
 */
public class DeadLine extends Task {

    /**
     * Creates a new instance of DeadLine labeled "D".
     *
     * @param s description of the task.
     * @param t time of the task.
     */
    public DeadLine(String s, String t) {
        super(s, t);
        this.label = "D";
    }

    /**
     * Returns a line with details of the DeadLine task.
     *
     * @return String representing the task.
     */
    public String toString() {
        return "[" + this.label + "]" + this.getStatusIcon() + this.description + " (by: " + this.time + ")";
    }
}
