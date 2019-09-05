import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is a class for deadline tasks.
 * @author Choong Yong Xin
 */

public class Deadline extends Task {

    Date by;
    String deadLine;

    /**
     * Constructor for Deadline.
     *
     * @param description Deadline description.
     * @param deadLine Date that task is due.
     */
    public Deadline(String description, String deadLine) {
        super(description);
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            this.by = dateFormat.parse(deadLine);
            this.deadLine = deadLine;
        } catch (Exception e) {
            this.deadLine = deadLine;
        }
    }

    /**
     * Returns string for task display.
     *
     * @return Display string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadLine + ")";
    }

    /**
     * Returns string for file writing.
     *
     * @return String to be saved.
     */
    @Override
    public String stringForAppend() {
        return "D | " + super.getStatusIcon() + " | " + description + " | " + deadLine;
    }
}