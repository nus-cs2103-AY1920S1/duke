/**
 * This is a class for deadline tasks.
 * @author Choong Yong Xin
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    Date by;
    String deadLine;

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