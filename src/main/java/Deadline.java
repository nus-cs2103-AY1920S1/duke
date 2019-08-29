<<<<<<< HEAD:src/main/java/DeadLine.java
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
=======
public class Deadline extends Task {

    public Deadline(String s, String t) {
>>>>>>> branch-A-CodingStandard:src/main/java/Deadline.java
        super(s, t);
        this.label = "D";
    }

<<<<<<< HEAD:src/main/java/DeadLine.java
    /**
     * Returns a line with details of the DeadLine task.
     *
     * @return String representing the task.
     */
=======
    @Override
>>>>>>> branch-A-CodingStandard:src/main/java/Deadline.java
    public String toString() {
        return "[" + this.label + "]" + this.getStatusIcon() + this.description + " (by: " + this.time + ")";
    }
}
